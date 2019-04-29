package com.geeboo.dyna.server.service.impl.course;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentReplyDTO;
import com.geeboo.dyna.server.mapper.course.IDynaCourseCommentReplyMapper;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentReplyService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaCourseCommentReplyServiceImpl implements IDynaCourseCommentReplyService {
    @Autowired
    private IDynaCourseCommentReplyMapper dynaCourseCommentReplyMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaCourseCommentReplyDTO dto) {
        dynaCourseCommentReplyMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaCourseCommentReplyDTO dto) {
        dynaCourseCommentReplyMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaCourseCommentReplyId) {
        dynaCourseCommentReplyMapper.deleteDynaCourseCommentReply(dynaCourseCommentReplyId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public BaseResponse deleteByCommentId(Integer commentId) {
        dynaCourseCommentReplyMapper.deleteByCommentId(commentId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaCourseCommentReplyDTO> page(DynaCourseCommentReplyDTO dto, Page<DynaCourseCommentReplyDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaCourseCommentReplyDTO> list = dynaCourseCommentReplyMapper.query(dto);
        com.github.pagehelper.Page<DynaCourseCommentReplyDTO> result =
                (com.github.pagehelper.Page<DynaCourseCommentReplyDTO>) list;
        TableResultResponse<DynaCourseCommentReplyDTO> response =
                new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public TableResultResponse<DynaCourseCommentReplyDTO> query(DynaCourseCommentReplyDTO dto) {
        List<DynaCourseCommentReplyDTO> list = dynaCourseCommentReplyMapper.query(dto);
        com.github.pagehelper.Page<DynaCourseCommentReplyDTO> result =
                (com.github.pagehelper.Page<DynaCourseCommentReplyDTO>) list;
        TableResultResponse<DynaCourseCommentReplyDTO> response =
                new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaCourseCommentReplyDTO> findById(Integer id) {
        DynaCourseCommentReplyDTO dto = dynaCourseCommentReplyMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaCourseCommentReplyDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaCourseCommentReplyDTO> findByCondition(DynaCourseCommentReplyDTO dto) {
        DynaCourseCommentReplyDTO model = dynaCourseCommentReplyMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaCourseCommentReplyDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }
}
