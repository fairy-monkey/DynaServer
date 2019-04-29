package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicContentDTO;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicContentMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicContentService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class DynaTopicContentServiceImpl implements IDynaTopicContentService {
    @Autowired
    private IDynaTopicContentMapper dynaTopicContentMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaTopicContentDTO dto) {
        dynaTopicContentMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaTopicContentDTO dto) {
        dynaTopicContentMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicContentId) {
        dynaTopicContentMapper.deleteDynaTopicContent(dynaTopicContentId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaTopicContentDTO> page(DynaTopicContentDTO dto, Page<DynaTopicContentDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaTopicContentDTO> list = dynaTopicContentMapper.query(dto);
        com.github.pagehelper.Page<DynaTopicContentDTO> result = (com.github.pagehelper.Page<DynaTopicContentDTO>) list;
        TableResultResponse<DynaTopicContentDTO> response =
            new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaTopicContentDTO> findById(Integer id) {
        DynaTopicContentDTO dto = dynaTopicContentMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaTopicContentDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaTopicContentDTO> findByCondition(DynaTopicContentDTO dto) {
        DynaTopicContentDTO model = dynaTopicContentMapper.findByCondition(dto);
        if (model != null) {
            ObjectResponse<DynaTopicContentDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public DynaTopicContentDTO findByTopicId(Integer topicId) {
        return dynaTopicContentMapper.findByTopicId(topicId);
    }

    @Override
    public List<DynaTopicContentDTO> batchGetContentByTopicId(Set<Integer> topicIdSet) {
        return dynaTopicContentMapper.batchGetContentByTopicIdSet(topicIdSet);
    }

    @Override
    public List<DynaTopicContentDTO> batchGetContentByTopicId(Map<Integer, ?> topicIdMap) {
        return dynaTopicContentMapper.batchGetContentByTopicIdMap(topicIdMap);
    }
}


