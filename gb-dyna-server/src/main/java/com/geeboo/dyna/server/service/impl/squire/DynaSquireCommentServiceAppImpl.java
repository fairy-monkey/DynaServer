package com.geeboo.dyna.server.service.impl.squire;

import java.util.List;

import com.geeboo.dyna.server.service.squire.IDynaSquireCommentAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.eventbus.center.squire.SquireEventBusCenter;
import com.geeboo.dyna.server.eventbus.dto.squire.SquireEventDTO;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireCommentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DynaSquireCommentServiceAppImpl implements IDynaSquireCommentAppService {
    @Autowired
    private IDynaSquireCommentMapper dynaSquireCommentMapper;

    @Override
    public TableResultResponse<DynaSquireCommentListDTO> page(DynaSquireCommentListDTO dto,
                                                              Page<DynaSquireCommentListDTO> page) {
        Integer dynaSquireId = dto.getDynaSquireId();
        Integer dynaSquireCommentId = dto.getDynaSquireCommentId();
        Integer pageSize = page.getPageSize();
        List<DynaSquireCommentListDTO> list = dynaSquireCommentMapper.getCommentListByLastId(dynaSquireId,
                dynaSquireCommentId, pageSize);
        TableResultResponse response = new TableResultResponse<>(0, 0, list);
        return response;
    }

    @Override
    public DynaSquireCommentListDTO getCommentDetail(Integer id) {
        DynaSquireCommentListDTO dto = dynaSquireCommentMapper.getCommentDetail(id);
        if (dto == null) {
            return dto;
        }
        dto.setNumReply(null);
        dto.setNumFavor(null);
        return dto;
    }

    @Transactional
    @Override
    public ObjectResponse<DynaSquireCommentDTO> addComment(DynaSquireCommentDTO dto) {
        if (dto.getCommentType().intValue() == 1) {
            dto.setToUserId(0);
        }
        dto.setNumFavor(0);
        dto.setNumReply(0);
        dto.setIsDel(0);
        dto.setIndexNo(0);
        dto.setBookScore(0);
        log.info("新增评论: dto: {}", dto);
        dynaSquireCommentMapper.add(dto);
        SquireEventBusCenter.post(
                new SquireEventDTO().bussinessId(dto.getDynaSquireId()).commentNum(true).operate(OperateEnum.ADD));
        DynaSquireCommentDTO resultDTO = new DynaSquireCommentDTO();
        resultDTO.setDynaSquireCommentId(dto.getDynaSquireCommentId());
        return new ObjectResponse<>().data(resultDTO).rel(true);
    }

    @Transactional
    @Override
    public BaseResponse deleteComment(DynaSquireCommentDTO dto) {
        DynaSquireCommentDTO oldDTO = dynaSquireCommentMapper.findById(dto.getDynaSquireCommentId());
        if (oldDTO == null) {
            log.info("评论不存在，删除成功, dto: {}", dto);
            return BaseResponse.success("删除成功");
        }
        if (!oldDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.NO_ENTRY.getStatus(), "只能删除自己发表的评论");
        }
        log.info("删除评论: dto: {}", dto);
        dynaSquireCommentMapper.deleteDynaSquireComment(dto.getDynaSquireCommentId());
        SquireEventBusCenter.post(new SquireEventDTO().bussinessId(dto.getDynaSquireId()).commentNum(true)
                .operate(OperateEnum.DELETE));
        return BaseResponse.success("删除成功");
    }

    @Override
    public BaseResponse deleteComment(DynaSquireCommentListDTO dto) {
        DynaSquireCommentDTO oldDTO = dynaSquireCommentMapper.findById(dto.getDynaSquireCommentId());
        if (oldDTO == null) {
            log.info("评论不存在，删除成功, dto: {}", dto);
            return BaseResponse.success("删除成功");
        }
        if (!oldDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.NO_ENTRY.getStatus(), "只能删除自己发表的评论");
        }
        log.info("删除评论: dto: {}", dto);
        dynaSquireCommentMapper.deleteDynaSquireComment(dto.getDynaSquireCommentId());
        SquireEventBusCenter.post(new SquireEventDTO().bussinessId(dto.getDynaSquireId()).commentNum(true)
                .operate(OperateEnum.DELETE));
        return BaseResponse.success("删除成功");
    }


    @Override
    public Long countCommentBySquire(Integer squireId) {
        return dynaSquireCommentMapper.countCommentBySquire(squireId);
    }

    @Transactional
    @Override
    public BaseResponse updateComment(DynaSquireCommentDTO dto) {
        dynaSquireCommentMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public ObjectResponse<DynaSquireCommentDTO> getById(DynaSquireCommentDTO dto) {
        DynaSquireCommentDTO resultDTO = dynaSquireCommentMapper.findById(dto.getDynaSquireCommentId());
        ObjectResponse<DynaSquireCommentDTO> response = new ObjectResponse<>();
        return response.data(resultDTO);
    }
}
