package com.geeboo.dyna.server.service.impl.course;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.course.DynaCourseComplaintDTO;
import com.geeboo.dyna.server.entity.course.DynaCourseCommentDO;
import com.geeboo.dyna.server.entity.course.DynaCourseCommentReplyDO;
import com.geeboo.dyna.server.mapper.course.IDynaCourseCommentMapper;
import com.geeboo.dyna.server.mapper.course.IDynaCourseCommentReplyMapper;
import com.geeboo.dyna.server.mapper.course.IDynaCourseComplaintMapper;
import com.geeboo.dyna.server.service.course.IDynaCourseComplaintService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaCourseComplaintServiceImpl implements IDynaCourseComplaintService {
    @Autowired
    private IDynaCourseComplaintMapper dynaCourseComplaintMapper;

    @Autowired
    private IDynaCourseCommentReplyMapper dynaCourseCommentReplyMapper;

    @Autowired
    private IDynaCourseCommentMapper dynaCourseCommentMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaCourseComplaintDTO dto) {
        dynaCourseComplaintMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaCourseComplaintDTO dto) {
        int complaintStatus = dto.getComplaintStatus();
        int resultCount = dynaCourseComplaintMapper.update(dto);
        if (resultCount > 0) {
            if (dto.getComplaintObj() == 1) {
                DynaCourseCommentDO commentDO = new DynaCourseCommentDO();
                commentDO.setDynaCourseCommentId(dto.getContentId());
                if (complaintStatus == 1) {
                    commentDO.setIsDel(0);
                    dynaCourseCommentMapper.updateByPrimaryKeySelective(commentDO);
                } else if (complaintStatus == 2) {
                    commentDO.setIsDel(1);
                    dynaCourseCommentMapper.updateByPrimaryKeySelective(commentDO);
                }
            } else {
                DynaCourseCommentReplyDO replyDO = new DynaCourseCommentReplyDO();
                replyDO.setDynaCourseCommentReplyId(dto.getContentId());
                if (complaintStatus == 1) {
                    replyDO.setIsDel(0);
                    dynaCourseCommentReplyMapper.updateByPrimaryKeySelective(replyDO);
                } else if (complaintStatus == 2) {
                    replyDO.setIsDel(1);
                    dynaCourseCommentReplyMapper.updateByPrimaryKeySelective(replyDO);
                }
            }

        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicComplaintId) {
        dynaCourseComplaintMapper.deleteDynaCourseComplaint(dynaTopicComplaintId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaCourseComplaintDTO> page(DynaCourseComplaintDTO dto, Page<DynaCourseComplaintDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaCourseComplaintDTO> list = dynaCourseComplaintMapper.query(dto);
        com.github.pagehelper.Page<DynaCourseComplaintDTO> result = (com.github.pagehelper.Page<DynaCourseComplaintDTO>) list;
        TableResultResponse<DynaCourseComplaintDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaCourseComplaintDTO> findById(Integer id) {
        DynaCourseComplaintDTO dto = dynaCourseComplaintMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaCourseComplaintDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaCourseComplaintDTO> findByCondition(DynaCourseComplaintDTO dto) {
        DynaCourseComplaintDTO model = dynaCourseComplaintMapper.findByCondition(dto);
        if (model != null) {
            ObjectResponse<DynaCourseComplaintDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }
}


