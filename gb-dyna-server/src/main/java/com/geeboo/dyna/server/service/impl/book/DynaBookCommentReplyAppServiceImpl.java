package com.geeboo.dyna.server.service.impl.book;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookReplyListDTO;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.mapper.book.IDynaBookCommentReplyMapper;
import com.geeboo.dyna.server.service.book.IDynaBookCommentAppService;
import com.geeboo.dyna.server.service.book.IDynaBookCommentReplyAppService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/15 18:29
 */
@Slf4j
@Service
public class DynaBookCommentReplyAppServiceImpl implements IDynaBookCommentReplyAppService {
    @Autowired
    private IDynaBookCommentAppService dynaBookCommentAppService;
    @Autowired
    private IDynaBookCommentReplyMapper dynaBookCommentReplyMapper;

    @Override
    public TableResultResponse<DynaBookReplyListDTO> getReplyPage(PageRestRequest<DynaBookReplyListDTO> page) {
        PageHelper.startPage(page.getPage().getPageNo(), page.getPage().getPageSize());
        com.github.pagehelper.Page<DynaBookReplyListDTO> list = dynaBookCommentReplyMapper
                    .queryReplyList(page.getData());
        return new TableResultResponse<>(list.getTotal(), list.getPages(), list.getResult());
    }

    @Transactional
    @Override
    public ObjectResponse<DynaBookCommentReplyDTO> addReply(DynaBookCommentReplyDTO dto) {
        // 回复类型 1：评论 2：回复
        if (dto.getReplyType().intValue() == 1) {
            dto.setToCommentReplyId(0);
            dto.setToUserId(0);
        } else {
            DynaBookCommentReplyDTO toReplyDTO = dynaBookCommentReplyMapper.findById(dto.getToCommentReplyId());
            if (toReplyDTO == null) {
                return ObjectResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "被回复的回复不存在");
            }
            dto.setToUserId(toReplyDTO.getUserId());
        }
        dto.setIsDel(0);
        dynaBookCommentReplyMapper.add(dto);
        log.info("新增回复, dto: {}", dto);
        dynaBookCommentAppService.incrementCommentCount(dto, OperateEnum.ADD);
        DynaBookCommentReplyDTO resultDTO = new DynaBookCommentReplyDTO();
        resultDTO.setDynaBookCommentReplyId(dto.getDynaBookCommentReplyId());
        resultDTO.setToUserId(dto.getToUserId());
        return new ObjectResponse<>().data(resultDTO).rel(true);
    }

    @Transactional
    @Override
    public BaseResponse deleteReply(DynaBookCommentReplyDTO dto) {
        DynaBookCommentReplyDTO oldDTO = dynaBookCommentReplyMapper.findById(dto.getDynaBookCommentReplyId());
        if (oldDTO == null) {
            log.info("回复不存在，删除成功, dto: {}", dto);
            return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
        }
        if (!oldDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "只能删除自己发表的回复");
        }
        dynaBookCommentReplyMapper.deleteDynaBookCommentReply(dto.getDynaBookCommentReplyId());
        log.info("删除回复, dto: {}", dto);
        dynaBookCommentAppService.incrementCommentCount(dto, OperateEnum.DELETE);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public Long countReplyByComment(Integer dynaBookCommentId) {
        return dynaBookCommentReplyMapper.countReplyByComment(dynaBookCommentId);
    }

    @Transactional
    @Override
    public BaseResponse updateReply(DynaBookCommentReplyDTO dto) {
        dynaBookCommentReplyMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }
}
