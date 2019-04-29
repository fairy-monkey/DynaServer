package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicStatDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicStatDO;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicStatMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicStatService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class DynaTopicStatServiceImpl implements IDynaTopicStatService {
    @Autowired
    private IDynaTopicStatMapper dynaTopicStatMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaTopicStatDTO dto) {
        dynaTopicStatMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaTopicStatDTO dto) {
        dynaTopicStatMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicStatId) {
        dynaTopicStatMapper.deleteDynaTopicStat(dynaTopicStatId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaTopicStatDTO> page(DynaTopicStatDTO dto, Page<DynaTopicStatDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaTopicStatDTO> list = dynaTopicStatMapper.query(dto);
        com.github.pagehelper.Page<DynaTopicStatDTO> result = (com.github.pagehelper.Page<DynaTopicStatDTO>) list;
        TableResultResponse<DynaTopicStatDTO> response =
                new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public TableResultResponse<DynaTopicStatDTO> query(DynaTopicStatDTO dto) {
        List<DynaTopicStatDTO> list = dynaTopicStatMapper.query(dto);
        TableResultResponse<DynaTopicStatDTO> response = new TableResultResponse<>(list.size(), list);
        return response;
    }

    @Override
    public ObjectResponse<DynaTopicStatDTO> findById(Integer id) {
        DynaTopicStatDTO dto = dynaTopicStatMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaTopicStatDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaTopicStatDTO> findByCondition(DynaTopicStatDTO dto) {
        DynaTopicStatDTO model = dynaTopicStatMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaTopicStatDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public DynaTopicStatDTO findByTopicId(Integer topicId) {
        return dynaTopicStatMapper.findByTopicId(topicId);
    }

    @Override
    public ObjectResponse<Map<Integer, DynaTopicStatDTO>> batchGetStatByTopicId(Set<Integer> topicIdSet) {
        if (topicIdSet == null || topicIdSet.isEmpty() || topicIdSet.size() > 1000) {
            return ObjectResponse.failure(StatusEnum.BAD_REQUEST.getStatus(), "集合不能为空或大于1K");
        }
        try {
            List<DynaTopicStatDTO> list = dynaTopicStatMapper.batchGetStatByTopicId(topicIdSet);
            Map<Integer, DynaTopicStatDTO> map = Maps.newHashMapWithExpectedSize(list.size());
            for (DynaTopicStatDTO dto : list) {
                map.put(dto.getDynaTopicId(), dto);
            }
            return new ObjectResponse<>().data(map).rel(true);
        } catch (Exception e) {
            log.error("查询异常, topicIdSet: {}", topicIdSet, e);
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), e.getMessage());
        }
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(DynaTopicStatDO dynaTopicStatDO) {
        return dynaTopicStatMapper.updateByPrimaryKeySelective(dynaTopicStatDO);
    }
}


