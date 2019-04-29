package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicReplyListDTO;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicCommentReplyMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicAppService;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentAppService;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentReplyAppService;
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
public class DynaTopicCommentReplyAppServiceImpl implements IDynaTopicCommentReplyAppService {
    @Autowired
    private IDynaTopicAppService dynaTopicAppService;
    @Autowired
    private IDynaTopicCommentAppService dynaTopicCommentAppService;
    @Autowired
    private IDynaTopicCommentReplyMapper dynaTopicCommentReplyMapper;

    @Override
    public TableResultResponse<DynaTopicReplyListDTO> getReplyPage(PageRestRequest<DynaTopicReplyListDTO> page) {
        PageHelper.startPage(page.getPage().getPageNo(), page.getPage().getPageSize());
        List<DynaTopicReplyListDTO> list = dynaTopicCommentReplyMapper.queryReplyList(page.getData());
        com.github.pagehelper.Page<DynaTopicReplyListDTO> result =
                (com.github.pagehelper.Page<DynaTopicReplyListDTO>) list;
        return new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
    }

    @Transactional
    @Override
    public ObjectResponse<DynaTopicCommentReplyDTO> addReply(DynaTopicCommentReplyDTO dto) {
        if (dto.getReplyType().intValue() == 1) {
            dto.setToCommentReplyId(0);
            dto.setToUserId(0);
        } else {
            DynaTopicCommentReplyDTO toReplyDTO = dynaTopicCommentReplyMapper.findById(dto.getToCommentReplyId());
            if (toReplyDTO == null) {
                return ObjectResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "被回复的回复不存在");
            }
            dto.setToUserId(toReplyDTO.getUserId());
        }
        dto.setIsDel(0);
        dynaTopicCommentReplyMapper.add(dto);
        log.info("新增回复, dto: {}", dto);
        dynaTopicCommentAppService.incrementCommentCount(dto, OperateEnum.ADD);
        dynaTopicAppService.incrementTopicCount(dto, OperateEnum.ADD);
        DynaTopicCommentReplyDTO resultDTO = new DynaTopicCommentReplyDTO();
        resultDTO.setDynaTopicCommentReplyId(dto.getDynaTopicCommentReplyId());
        resultDTO.setToUserId(dto.getToUserId());
        return new ObjectResponse<>().data(resultDTO).rel(true);
    }

    @Transactional
    @Override
    public BaseResponse deleteReply(DynaTopicCommentReplyDTO dto) {
        DynaTopicCommentReplyDTO oldDTO = dynaTopicCommentReplyMapper.findById(dto.getDynaTopicCommentReplyId());
        if (oldDTO == null) {
            log.info("回复不存在，删除成功, dto: {}", dto);
            return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
        }
        if (!oldDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.VERIFICATION_FAILURE.getStatus(), "只能删除自己发表的回复");
        }
        dynaTopicCommentReplyMapper.deleteDynaTopicCommentReply(dto.getDynaTopicCommentReplyId());
        log.info("删除回复, dto: {}", dto);
        dynaTopicCommentAppService.incrementCommentCount(dto, OperateEnum.DELETE);
        dynaTopicAppService.incrementTopicCount(dto, OperateEnum.DELETE);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public Long countReplyByComment(Integer dynaTopicCommentId) {
        return dynaTopicCommentReplyMapper.countReplyByComment(dynaTopicCommentId);
    }
}
