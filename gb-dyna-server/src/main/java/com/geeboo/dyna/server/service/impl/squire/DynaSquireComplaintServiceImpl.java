package com.geeboo.dyna.server.service.impl.squire;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireComplaintDTO;
import com.geeboo.dyna.server.constant.SquireCacheConstant;
import com.geeboo.dyna.server.entity.squire.DynaSquireCommentDO;
import com.geeboo.dyna.server.entity.squire.DynaSquireDO;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireCommentMapper;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireComplaintMapper;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireComplaintService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaSquireComplaintServiceImpl implements IDynaSquireComplaintService {
    @Autowired
    private IDynaSquireComplaintMapper dynaSquireComplaintMapper;

    @Autowired
    private IDynaSquireCommentMapper dynaSquireCommentMapper;

    @Autowired
    private IDynaSquireMapper dynaSquireMapper;

    @Autowired
    private ValueOperations valueOperations;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public BaseResponse add(DynaSquireComplaintDTO dto) {
        dynaSquireComplaintMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaSquireComplaintDTO dto) {
        int complaintStatus = dto.getComplaintStatus();
        int resultCount = dynaSquireComplaintMapper.update(dto);
        if (resultCount > 0) {
            if (dto.getComplaintObj() == 1) {
                DynaSquireDO squireDO = new DynaSquireDO();
                squireDO.setDynaSquireId(dto.getDynaSquireId());
                if (complaintStatus == 1) {
                    squireDO.setIsDel(0);
                    dynaSquireMapper.updateByPrimaryKeySelective(squireDO);
                } else if (complaintStatus == 2) {
                    squireDO.setIsDel(1);
                    dynaSquireMapper.updateByPrimaryKeySelective(squireDO);
                }
            } else {
                DynaSquireCommentDO commentDO = new DynaSquireCommentDO();
                commentDO.setDynaSquireCommentId(dto.getContentId());
                if (complaintStatus == 1) {
                    commentDO.setIsDel(0);
                    dynaSquireCommentMapper.updateByPrimaryKeySelective(commentDO);
                    //如果redis中存在先清除缓存
                    delRedis(dto.getDynaSquireId());
                    //取消屏蔽,回复数加一
                    Integer num = getNumReply(dto.getDynaSquireId());
                    DynaSquireDO dynaSquireDO = new DynaSquireDO();
                    dynaSquireDO.setDynaSquireId(dto.getDynaSquireId());
                    dynaSquireDO.setNumReply(++num);
                    dynaSquireMapper.updateByPrimaryKeySelective(dynaSquireDO);
                } else if (complaintStatus == 2) {
                    commentDO.setIsDel(1);
                    dynaSquireCommentMapper.updateByPrimaryKeySelective(commentDO);
                    //如果redis中存在先清除缓存
                    delRedis(dto.getDynaSquireId());
                    //屏蔽,回复数减一
                    Integer num = getNumReply(dto.getDynaSquireId());
                    DynaSquireDO dynaSquireDO = new DynaSquireDO();
                    dynaSquireDO.setDynaSquireId(dto.getDynaSquireId());
                    dynaSquireDO.setNumReply(--num);
                    dynaSquireMapper.updateByPrimaryKeySelective(dynaSquireDO);
                }
            }
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    private Integer getNumReply(Integer dynaSquireId) {
        Integer numReply = dynaSquireMapper.getNumReply(dynaSquireId);
        return numReply;
    }

    private void delRedis(Integer dynaSquireId) {
        String rediskey = SquireCacheConstant.SQUIRE_COMMENT_NUM_PREFIX + dynaSquireId;
        if (valueOperations.get(rediskey) != null) {
            redisTemplate.delete(rediskey);
        }
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaTopicComplaintId) {
        dynaSquireComplaintMapper.deleteDynaSquireComplaint(dynaTopicComplaintId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaSquireComplaintDTO> page(DynaSquireComplaintDTO dto, Page<DynaSquireComplaintDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaSquireComplaintDTO> list = dynaSquireComplaintMapper.query(dto);
        com.github.pagehelper.Page<DynaSquireComplaintDTO> result = (com.github.pagehelper.Page<DynaSquireComplaintDTO>) list;
        TableResultResponse<DynaSquireComplaintDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaSquireComplaintDTO> findById(Integer id) {
        DynaSquireComplaintDTO dto = dynaSquireComplaintMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaSquireComplaintDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaSquireComplaintDTO> findByCondition(DynaSquireComplaintDTO dto) {
        DynaSquireComplaintDTO model = dynaSquireComplaintMapper.findByCondition(dto);
        if (model != null) {
            ObjectResponse<DynaSquireComplaintDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }
}


