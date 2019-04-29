package com.geeboo.dyna.server.service.impl.course;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseReplyListDTO;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.mapper.course.IDynaCourseCommentReplyMapper;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentAppService;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentReplyAppService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/15 18:29
 */
@Slf4j
@Service
public class DynaCourseCommentReplyAppServiceImpl implements IDynaCourseCommentReplyAppService {
    @Autowired
    private IDynaCourseCommentAppService dynaCourseCommentAppService;
    @Autowired
    private IDynaCourseCommentReplyMapper dynaCourseCommentReplyMapper;

    @Override
    public TableResultResponse<DynaCourseReplyListDTO> getReplyPage(PageRestRequest<DynaCourseReplyListDTO> page) {
        PageHelper.startPage(page.getPage().getPageNo(), page.getPage().getPageSize());
        List<DynaCourseReplyListDTO> list = dynaCourseCommentReplyMapper.queryReplyList(page.getData());
        com.github.pagehelper.Page<DynaCourseReplyListDTO> result = (com.github.pagehelper.Page<DynaCourseReplyListDTO>) list;
        return new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
    }

    @Transactional
    @Override
    public ObjectResponse<DynaCourseCommentReplyDTO> addReply(DynaCourseCommentReplyDTO dto) {
        if (dto.getReplyType().intValue() == 1) {
            dto.setToCommentReplyId(0);
            dto.setToUserId(0);
        } else {
            DynaCourseCommentReplyDTO toReplyDTO = dynaCourseCommentReplyMapper.findById(dto.getToCommentReplyId());
            if (toReplyDTO == null) {
                return ObjectResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "被回复的回复不存在");
            }
            dto.setToUserId(toReplyDTO.getUserId());
        }
        dto.setIsDel(0);
        dynaCourseCommentReplyMapper.add(dto);
        log.info("新增回复, dto: {}", dto);
        dynaCourseCommentAppService.incrementCommentCount(dto, OperateEnum.ADD);
        DynaCourseCommentReplyDTO resultDTO = new DynaCourseCommentReplyDTO();
        resultDTO.setDynaCourseCommentReplyId(dto.getDynaCourseCommentReplyId());
        resultDTO.setToUserId(dto.getToUserId());
        return new ObjectResponse<>().data(resultDTO).rel(true);
    }

    @Transactional
    @Override
    public BaseResponse deleteReply(DynaCourseCommentReplyDTO dto) {
        DynaCourseCommentReplyDTO oldDTO = dynaCourseCommentReplyMapper.findById(dto.getDynaCourseCommentReplyId());
        if (oldDTO == null) {
            log.info("回复不存在，删除成功, dto: {}", dto);
            return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
        }
        if (!oldDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "只能删除自己发表的回复");
        }
        dynaCourseCommentReplyMapper.deleteDynaCourseCommentReply(dto.getDynaCourseCommentReplyId());
        log.info("删除回复, dto: {}", dto);
        dynaCourseCommentAppService.incrementCommentCount(dto, OperateEnum.DELETE);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public Long countReplyByComment(Integer dynaCourseCommentId) {
        return dynaCourseCommentReplyMapper.countReplyByComment(dynaCourseCommentId);
    }

    @Transactional
    @Override
    public BaseResponse updateReply(DynaCourseCommentReplyDTO dto) {
        dynaCourseCommentReplyMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }
}
