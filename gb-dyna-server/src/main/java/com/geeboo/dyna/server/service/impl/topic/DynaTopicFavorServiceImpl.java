package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicFavorDTO;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicFavorMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicFavorService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaTopicFavorServiceImpl implements IDynaTopicFavorService {
    @Autowired
    private IDynaTopicFavorMapper dynaTopicFavorMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaTopicFavorDTO dto) {
        dynaTopicFavorMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaTopicFavorDTO dto) {
        dynaTopicFavorMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicFavorId) {
        dynaTopicFavorMapper.deleteDynaTopicFavor(dynaTopicFavorId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaTopicFavorDTO> page(DynaTopicFavorDTO dto, Page<DynaTopicFavorDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaTopicFavorDTO> list = dynaTopicFavorMapper.query(dto);
        com.github.pagehelper.Page<DynaTopicFavorDTO> result = (com.github.pagehelper.Page<DynaTopicFavorDTO>) list;
        TableResultResponse<DynaTopicFavorDTO> response =
            new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaTopicFavorDTO> findById(Integer id) {
        DynaTopicFavorDTO dto = dynaTopicFavorMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaTopicFavorDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaTopicFavorDTO> findByCondition(DynaTopicFavorDTO dto) {
        DynaTopicFavorDTO model = dynaTopicFavorMapper.findByCondition(dto);
        if (model != null) {
            ObjectResponse<DynaTopicFavorDTO> response = new ObjectResponse<>();
            return response.data(model).rel(true);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }
}


