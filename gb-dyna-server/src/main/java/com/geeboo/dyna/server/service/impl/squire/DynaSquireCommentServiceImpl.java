package com.geeboo.dyna.server.service.impl.squire;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.eventbus.center.squire.SquireEventBusCenter;
import com.geeboo.dyna.server.eventbus.dto.squire.SquireEventDTO;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireCommentMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireCommentService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DynaSquireCommentServiceImpl implements IDynaSquireCommentService {
    @Autowired
    private IDynaSquireCommentMapper dynaSquireCommentMapper;



    @Override
    public TableResultResponse<DynaSquireCommentListDTO> page(DynaSquireCommentListDTO dto,
                                                              Page<DynaSquireCommentListDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaSquireCommentListDTO> list = dynaSquireCommentMapper.query(dto);
        com.github.pagehelper.Page<DynaSquireCommentListDTO> result = (com.github.pagehelper.Page<DynaSquireCommentListDTO>) list;
        TableResultResponse<DynaSquireCommentListDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public BaseResponse deleteByDynaSquireId(Integer dynaSquireId) {
        dynaSquireCommentMapper.deleteByDynaSquireId(dynaSquireId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }


    @Override
    public BaseResponse delete(DynaSquireCommentListDTO dto) {
        DynaSquireCommentDTO oldDTO = dynaSquireCommentMapper.findById(dto.getDynaSquireCommentId());
        if (oldDTO == null) {
            log.info("评论不存在，删除成功, dto: {}", dto);
            return BaseResponse.success("删除成功");
        }
        log.info("删除评论: dto: {}", dto);
        dynaSquireCommentMapper.deleteDynaSquireComment(dto.getDynaSquireCommentId());
        SquireEventBusCenter.post(new SquireEventDTO().bussinessId(dto.getDynaSquireId()).commentNum(true)
                .operate(OperateEnum.DELETE));

        return BaseResponse.success("删除成功");
    }


}
