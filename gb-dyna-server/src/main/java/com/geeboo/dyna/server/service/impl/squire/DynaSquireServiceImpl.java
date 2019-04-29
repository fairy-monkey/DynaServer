package com.geeboo.dyna.server.service.impl.squire;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;
import com.geeboo.dyna.server.constant.SquireCacheConstant;
import com.geeboo.dyna.server.entity.squire.DynaSquireDO;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireCommentService;
import com.geeboo.dyna.server.service.squire.IDynaSquireService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class DynaSquireServiceImpl implements IDynaSquireService {

    @Autowired
    private IDynaSquireMapper dynaSquireMapper;
    @Autowired
    private IDynaSquireCommentService dynaSquireCommentService;

    @Autowired
    private IRedisService redisService;


    @Override
    public TableResultResponse<DynaSquireDTO> page(DynaSquireDTO dto, Page<DynaSquireDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaSquireDTO> list = dynaSquireMapper.query(dto);
        com.github.pagehelper.Page<DynaSquireDTO> result = (com.github.pagehelper.Page<DynaSquireDTO>) list;
        TableResultResponse<DynaSquireDTO> response = new TableResultResponse<>(result.getTotal(), result.getPages(), result.getResult());
        return response;
    }

    @Override
    public BaseResponse delete(Integer dynaSquireId) {
        String lockKey = SquireCacheConstant.SQUIRE_OBJECT_PREFIX + dynaSquireId;
        Optional<String> identifier = redisService.lock(lockKey, 3, TimeUnit.SECONDS);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "其他用户正在操作该评论，请稍后再试");
        }
        try {
            DynaSquireDO commentDO = dynaSquireMapper.selectByPrimaryKey(dynaSquireId);
            if (commentDO == null) {
                return BaseResponse.failure(StatusEnum.NOT_FOUND.getStatus(), "动态不存在");
            }
            dynaSquireMapper.deleteDynaSquire(dynaSquireId);
            dynaSquireCommentService.deleteByDynaSquireId(dynaSquireId);
        } finally {
            redisService.unlock(lockKey, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }



}


