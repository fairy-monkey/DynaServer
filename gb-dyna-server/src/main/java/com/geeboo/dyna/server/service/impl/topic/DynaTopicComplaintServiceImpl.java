package com.geeboo.dyna.server.service.impl.topic;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicComplaintDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicCommentDO;
import com.geeboo.dyna.server.entity.topic.DynaTopicCommentReplyDO;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicCommentMapper;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicCommentReplyMapper;
import com.geeboo.dyna.server.mapper.topic.IDynaTopicComplaintMapper;
import com.geeboo.dyna.server.service.topic.IDynaTopicComplaintService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaTopicComplaintServiceImpl implements IDynaTopicComplaintService {
    @Autowired
    private IDynaTopicComplaintMapper dynaTopicComplaintMapper;

    @Autowired
    private IDynaTopicCommentReplyMapper dynaTopicCommentReplyMapper;

    @Autowired
    private IDynaTopicCommentMapper dynaTopicCommentMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaTopicComplaintDTO dto) {
        dynaTopicComplaintMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaTopicComplaintDTO dto) {
        int complaintStatus = dto.getComplaintStatus();
        int resultCount = dynaTopicComplaintMapper.update(dto);
        if (resultCount > 0) {
            if (dto.getComplaintObj() == 1) {
                DynaTopicCommentDO commentDO = new DynaTopicCommentDO();
                commentDO.setDynaTopicCommentId(dto.getContentId());
                if (complaintStatus == 1) {
                    commentDO.setIsDel(0);
                    dynaTopicCommentMapper.updateByPrimaryKeySelective(commentDO);
                } else if (complaintStatus == 2) {
                    commentDO.setIsDel(1);
                    dynaTopicCommentMapper.updateByPrimaryKeySelective(commentDO);
                }
            } else {
                DynaTopicCommentReplyDO replyDO = new DynaTopicCommentReplyDO();
                replyDO.setDynaTopicCommentReplyId(dto.getContentId());
                if (complaintStatus == 1) {
                    replyDO.setIsDel(0);
                    dynaTopicCommentReplyMapper.updateByPrimaryKeySelective(replyDO);
                } else if (complaintStatus == 2) {
                    replyDO.setIsDel(1);
                    dynaTopicCommentReplyMapper.updateByPrimaryKeySelective(replyDO);
                }
            }

        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicComplaintId) {
        dynaTopicComplaintMapper.deleteDynaTopicComplaint(dynaTopicComplaintId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaTopicComplaintDTO> page(DynaTopicComplaintDTO dto, Page<DynaTopicComplaintDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaTopicComplaintDTO> list = dynaTopicComplaintMapper.query(dto);
        com.github.pagehelper.Page<DynaTopicComplaintDTO> result = (com.github.pagehelper.Page<DynaTopicComplaintDTO>) list;
        TableResultResponse<DynaTopicComplaintDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaTopicComplaintDTO> findById(Integer id) {
        DynaTopicComplaintDTO dto = dynaTopicComplaintMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaTopicComplaintDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaTopicComplaintDTO> findByCondition(DynaTopicComplaintDTO dto) {
        DynaTopicComplaintDTO model = dynaTopicComplaintMapper.findByCondition(dto);
        if (model != null) {
            ObjectResponse<DynaTopicComplaintDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }
}


