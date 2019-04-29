package com.geeboo.dyna.server.service.impl.book;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.book.DynaBookComplaintDTO;
import com.geeboo.dyna.server.entity.book.DynaBookCommentDO;
import com.geeboo.dyna.server.entity.book.DynaBookCommentReplyDO;
import com.geeboo.dyna.server.mapper.book.IDynaBookCommentMapper;
import com.geeboo.dyna.server.mapper.book.IDynaBookCommentReplyMapper;
import com.geeboo.dyna.server.mapper.book.IDynaBookComplaintMapper;
import com.geeboo.dyna.server.service.book.IDynaBookComplaintService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaBookComplaintServiceImpl implements IDynaBookComplaintService {
    @Autowired
    private IDynaBookComplaintMapper dynaBookComplaintMapper;

    @Autowired
    private IDynaBookCommentReplyMapper dynaBookCommentReplyMapper;

    @Autowired
    private IDynaBookCommentMapper dynaBookCommentMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaBookComplaintDTO dto) {
        dynaBookComplaintMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaBookComplaintDTO dto) {
        int complaintStatus = dto.getComplaintStatus();
        int resultCount = dynaBookComplaintMapper.update(dto);
        if (resultCount > 0) {
            if (dto.getComplaintObj() == 1) {
                DynaBookCommentDO commentDO = new DynaBookCommentDO();
                commentDO.setDynaBookCommentId(dto.getContentId());
                if (complaintStatus == 1) {
                    commentDO.setIsDel(0);
                    dynaBookCommentMapper.updateByPrimaryKeySelective(commentDO);
                } else if (complaintStatus == 2) {
                    commentDO.setIsDel(1);
                    dynaBookCommentMapper.updateByPrimaryKeySelective(commentDO);
                }
            } else {
                DynaBookCommentReplyDO replyDO = new DynaBookCommentReplyDO();
                replyDO.setDynaBookCommentReplyId(dto.getContentId());
                if (complaintStatus == 1) {
                    replyDO.setIsDel(0);
                    dynaBookCommentReplyMapper.updateByPrimaryKeySelective(replyDO);
                } else if (complaintStatus == 2) {
                    replyDO.setIsDel(1);
                    dynaBookCommentReplyMapper.updateByPrimaryKeySelective(replyDO);
                }
            }

        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicComplaintId) {
        dynaBookComplaintMapper.deleteDynaBookComplaint(dynaTopicComplaintId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaBookComplaintDTO> page(DynaBookComplaintDTO dto, Page<DynaBookComplaintDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaBookComplaintDTO> list = dynaBookComplaintMapper.query(dto);
        com.github.pagehelper.Page<DynaBookComplaintDTO> result = (com.github.pagehelper.Page<DynaBookComplaintDTO>) list;
        TableResultResponse<DynaBookComplaintDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaBookComplaintDTO> findById(Integer id) {
        DynaBookComplaintDTO dto = dynaBookComplaintMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaBookComplaintDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaBookComplaintDTO> findByCondition(DynaBookComplaintDTO dto) {
        DynaBookComplaintDTO model = dynaBookComplaintMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaBookComplaintDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }
}


