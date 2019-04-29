package com.geeboo.dyna.server.service.impl.book;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentReplyDTO;
import com.geeboo.dyna.server.mapper.book.IDynaBookCommentReplyMapper;
import com.geeboo.dyna.server.service.book.IDynaBookCommentReplyService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaBookCommentReplyServiceImpl implements IDynaBookCommentReplyService {
    @Autowired
    private IDynaBookCommentReplyMapper dynaBookCommentReplyMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaBookCommentReplyDTO dto) {
        dynaBookCommentReplyMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaBookCommentReplyDTO dto) {
        dynaBookCommentReplyMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaBookCommentReplyId) {
        dynaBookCommentReplyMapper.deleteDynaBookCommentReply(dynaBookCommentReplyId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public BaseResponse deleteByCommentId(Integer commentId) {
        dynaBookCommentReplyMapper.deleteByCommentId(commentId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaBookCommentReplyDTO> page(DynaBookCommentReplyDTO dto, Page<DynaBookCommentReplyDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaBookCommentReplyDTO> list = dynaBookCommentReplyMapper.query(dto);
        com.github.pagehelper.Page<DynaBookCommentReplyDTO> result =
                (com.github.pagehelper.Page<DynaBookCommentReplyDTO>) list;
        TableResultResponse<DynaBookCommentReplyDTO> response =
                new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public TableResultResponse<DynaBookCommentReplyDTO> query(DynaBookCommentReplyDTO dto) {
        List<DynaBookCommentReplyDTO> list = dynaBookCommentReplyMapper.query(dto);
        com.github.pagehelper.Page<DynaBookCommentReplyDTO> result =
                (com.github.pagehelper.Page<DynaBookCommentReplyDTO>) list;
        TableResultResponse<DynaBookCommentReplyDTO> response =
                new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaBookCommentReplyDTO> findById(Integer id) {
        DynaBookCommentReplyDTO dto = dynaBookCommentReplyMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaBookCommentReplyDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaBookCommentReplyDTO> findByCondition(DynaBookCommentReplyDTO dto) {
        DynaBookCommentReplyDTO model = dynaBookCommentReplyMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaBookCommentReplyDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }
}