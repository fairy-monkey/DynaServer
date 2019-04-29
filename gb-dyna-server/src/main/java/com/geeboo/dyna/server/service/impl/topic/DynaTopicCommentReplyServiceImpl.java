package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentReplyDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicCommentReplyDO;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicCommentReplyMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentReplyService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaTopicCommentReplyServiceImpl implements IDynaTopicCommentReplyService {
    @Autowired
    private IDynaTopicCommentReplyMapper dynaTopicCommentReplyMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaTopicCommentReplyDTO dto) {
        dynaTopicCommentReplyMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaTopicCommentReplyDTO dto) {
        dynaTopicCommentReplyMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicCommentReplyId) {
        dynaTopicCommentReplyMapper.deleteDynaTopicCommentReply(dynaTopicCommentReplyId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public BaseResponse deleteByCommentId(Integer commentId) {
        dynaTopicCommentReplyMapper.deleteByCommentId(commentId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaTopicCommentReplyDTO> page(DynaTopicCommentReplyDTO dto, Page<DynaTopicCommentReplyDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaTopicCommentReplyDTO> list = dynaTopicCommentReplyMapper.query(dto);
        com.github.pagehelper.Page<DynaTopicCommentReplyDTO> result = (com.github.pagehelper.Page<DynaTopicCommentReplyDTO>) list;
        TableResultResponse<DynaTopicCommentReplyDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaTopicCommentReplyDTO> findById(Integer id) {
        DynaTopicCommentReplyDTO dto = dynaTopicCommentReplyMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaTopicCommentReplyDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaTopicCommentReplyDTO> findByCondition(DynaTopicCommentReplyDTO dto) {
        DynaTopicCommentReplyDTO model = dynaTopicCommentReplyMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaTopicCommentReplyDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Transactional
    @Override
    public BaseResponse hiddenCommentReply(DynaTopicCommentReplyDTO dto) {
        DynaTopicCommentReplyDO commentReplyDO = dynaTopicCommentReplyMapper.selectByPrimaryKey(dto.getDynaTopicCommentReplyId());
        if (commentReplyDO == null) {
            return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "评论回复不存在");
        }
        commentReplyDO.setIsDel(dto.getIsDel());
        dynaTopicCommentReplyMapper.updateByPrimaryKeySelective(commentReplyDO);
        return BaseResponse.success("操作成功");
    }
}


