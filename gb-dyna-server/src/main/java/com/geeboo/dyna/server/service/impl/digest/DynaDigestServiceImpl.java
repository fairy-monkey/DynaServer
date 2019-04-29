package com.geeboo.dyna.server.service.impl.digest;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.common.qiniu.QiniuConfiguration;
import com.geeboo.common.util.DateUtil;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestDTO;
import com.geeboo.dyna.server.entity.digest.DynaDigestDO;
import com.geeboo.dyna.server.mapper.digest.IDynaDigestMapper;
import com.geeboo.dyna.server.service.digest.IDynaDigestService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class DynaDigestServiceImpl implements IDynaDigestService {
    @Autowired
    private IDynaDigestMapper dynaDigestMapper;
    @Autowired
    private QiniuConfiguration qiniuConfiguration;

    @Transactional
    @Override
    public BaseResponse add(DynaDigestDTO dto) {
        dto.setImagePath(qiniuConfiguration.removeDomain(dto.getImagePath()));
        dynaDigestMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaDigestDTO dto) {
        dto.setImagePath(qiniuConfiguration.removeDomain(dto.getImagePath()));
        dynaDigestMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse remove(Integer dynaDigestId) {
        dynaDigestMapper.remove(dynaDigestId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    public TableResultResponse<DynaDigestDTO> page(DynaDigestDTO dto, Page<DynaDigestDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaDigestDTO> list = dynaDigestMapper.query(dto);
        com.github.pagehelper.Page<DynaDigestDTO> result = (com.github.pagehelper.Page<DynaDigestDTO>) list;
        joinQiniu(list);
        TableResultResponse<DynaDigestDTO> response = new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    public TableResultResponse<DynaDigestDTO> query(DynaDigestDTO dto) {
        List<DynaDigestDTO> list = dynaDigestMapper.query(dto);
        joinQiniu(list);
        TableResultResponse<DynaDigestDTO> response = new TableResultResponse<>(list.size(), list);
        return response;
    }

    public ObjectResponse<DynaDigestDTO> findById(Integer dynaDigestId) {
        DynaDigestDTO dto = dynaDigestMapper.findById(dynaDigestId);
        if (dto != null) {
            joinQiniu(dto);
            ObjectResponse<DynaDigestDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    public ObjectResponse<DynaDigestDTO> findByCondition(DynaDigestDTO dto) {
        DynaDigestDTO model = dynaDigestMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaDigestDTO> response = new ObjectResponse<>();
            joinQiniu(model);
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Transactional
    @Override
    public BaseResponse publish(DynaDigestDTO dto) {
        DynaDigestDO dynaDigestDO = dynaDigestMapper.selectByPrimaryKey(dto.getDynaDigestId());
        if (dynaDigestDO == null) {
            return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "书摘不存在");
        } else if (dynaDigestDO.getPublishStatus().intValue() == 1) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "书摘已发布");
        }
        dynaDigestDO.setPublishStatus(1);
        dynaDigestDO.setPublishDate(DateUtil.getCurrentDate().intValue());
        dynaDigestMapper.updateByPrimaryKeySelective(dynaDigestDO);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse off(DynaDigestDTO dto) {
        DynaDigestDO dynaDigestDO = dynaDigestMapper.selectByPrimaryKey(dto.getDynaDigestId());
        if (dynaDigestDO == null) {
            return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "书摘不存在");
        } else if (dynaDigestDO.getPublishStatus().intValue() == 0) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "书摘已取消发布");
        }
        dynaDigestDO.setPublishStatus(0);
        dynaDigestMapper.updateByPrimaryKeySelective(dynaDigestDO);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public BaseResponse timeToPublish(DynaDigestDTO dto) {
        DynaDigestDO dynaDigestDO = dynaDigestMapper.selectByPrimaryKey(dto.getDynaDigestId());
        if (dynaDigestDO == null) {
            return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "书摘不存在");
        } else if (dynaDigestDO.getPublishStatus().intValue() == 1) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "书摘已发布");
        }
        dynaDigestDO.setPublishStatus(2);
        dynaDigestDO.setPublishDate(dto.getPublishDate());
        dynaDigestMapper.updateByPrimaryKeySelective(dynaDigestDO);
        return BaseResponse.success("定时发布成功");
    }

    private void joinQiniu(DynaDigestDTO dto) {
        dto.setImagePath(qiniuConfiguration.joinBookBucketDomain(dto.getImagePath()));
    }

    private void joinQiniu(List<DynaDigestDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(dto -> joinQiniu(dto));
    }
}