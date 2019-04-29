package com.geeboo.dyna.server.service.impl.squire;

import com.geeboo.cache.service.IRedisService;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.util.DateUtil;
import com.geeboo.common.util.JSONMapper;
import com.geeboo.dyna.server.client.dto.squire.*;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.constant.SquireCacheConstant;
import com.geeboo.dyna.server.entity.squire.DynaSquireDO;
import com.geeboo.dyna.server.eventbus.center.squire.SquireEventBusCenter;
import com.geeboo.dyna.server.eventbus.dto.squire.SquireEventDTO;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireMapper;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireUserStatMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireCommentAppService;
import com.geeboo.dyna.server.service.squire.IDynaSquireFavorService;
import com.geeboo.dyna.server.service.squire.IDynaSquireWriteService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/29 14:47
 */
@Slf4j
@Service
public class DynaSquireWriteServiceImpl implements IDynaSquireWriteService {
    /**
     * 切勿修改
     */
    private static String[] dtoIgnore = new String[]{"numSupport", "numReply", "isDisp", "isDel", "modifyTime", "modifyBy"};
    @Autowired
    private IRedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ZSetOperations zSetOperations;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaSquireFavorService dynaSquireFavorService;
    @Autowired
    private IDynaSquireCommentAppService dynaSquireCommentAppService;
    @Autowired
    private IDynaSquireMapper dynaSquireMapper;
    @Autowired
    private IDynaSquireUserStatMapper dynaSquireUserStatMapper;

    @Transactional
    @Override
    public BaseResponse update(DynaSquireDTO dto) {
        dynaSquireMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(DynaSquireDTO dto) {
        DynaSquireDTO dbDTO = dynaSquireMapper.findById(dto.getDynaSquireId());
        if (dbDTO == null) {
            log.info("记录不存在，删除成功, dto: {}", dto);
            return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
        }
        if (!dbDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.NO_ENTRY.getStatus(), "只能删除自己发表的动态");
        }
        log.info("删除动态, dto: {}", dto);
        dynaSquireMapper.deleteDynaSquire(dto.getDynaSquireId());
        SquireEventBusCenter.post(new SquireEventDTO().bussinessId(dto.getDynaSquireId()).operate(OperateEnum.DELETE)
                .squireCount(true));
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public ObjectResponse<DynaSquireNoteDTO> addSquireFromNote(DynaSquireNoteDTO dto) {
        DynaSquireDTO squireDTO = new DynaSquireDTO();
        BeanUtils.copyProperties(dto, squireDTO);
        squireDTO.setSquireBusType(SquireCacheConstant.BUSINESS_TYPE_NOTE);
        squireDTO.setBookScore(0);
        Integer id = this.add(squireDTO);
        this.addToCache(squireDTO);
        DynaSquireNoteDTO result = new DynaSquireNoteDTO();
        result.setDynaSquireId(id);
        return new ObjectResponse<>().data(result).rel(true);
    }

    @Transactional
    @Override
    public ObjectResponse<DynaSquireBookDTO> addSquireFromBook(DynaSquireBookDTO dto) {
        DynaSquireDTO squireDTO = new DynaSquireDTO();
        BeanUtils.copyProperties(dto, squireDTO);
        squireDTO.setSquireBusType(SquireCacheConstant.BUSINESS_TYPE_BOOK);
        squireDTO.setBookScore(0);
        squireDTO.setBusinessId(0);
        squireDTO.setSelectContent("");
        Integer id = this.add(squireDTO);
        this.addToCache(squireDTO);
        DynaSquireBookDTO result = new DynaSquireBookDTO();
        result.setDynaSquireId(id);
        return new ObjectResponse<>().data(result).rel(true);
    }

    @Transactional
    @Override
    public ObjectResponse<DynaSquireReadBookDTO> addSquireFromReadBook(DynaSquireReadBookDTO dto) {
        DynaSquireDTO squireDTO = new DynaSquireDTO();
        BeanUtils.copyProperties(dto, squireDTO);
        squireDTO.setSquireBusType(SquireCacheConstant.BUSINESS_TYPE_READ_BOOK);
        squireDTO.setBusinessId(0);
        squireDTO.setSelectContent("");
        Integer id = this.add(squireDTO);
        this.addToCache(squireDTO);
        DynaSquireReadBookDTO result = new DynaSquireReadBookDTO();
        result.setDynaSquireId(id);
        return new ObjectResponse<>().data(result).rel(true);
    }

    @Override
    public ObjectResponse<DynaSquireBookDTO> addSquireFromShareBook(DynaSquireBookDTO dto) {
        DynaSquireDTO squireDTO = new DynaSquireDTO();
        BeanUtils.copyProperties(dto, squireDTO);
        squireDTO.setSquireBusType(SquireCacheConstant.BUSINESS_TYPE_SHARE_BOOK);
        squireDTO.setBookScore(0);
        squireDTO.setBusinessId(0);
        squireDTO.setSelectContent("");
        Integer id = this.add(squireDTO);
        this.addToCache(squireDTO);
        DynaSquireBookDTO result = new DynaSquireBookDTO();
        result.setDynaSquireId(id);
        return new ObjectResponse<>().data(result).rel(true);
    }

    @Override
    public ObjectResponse<DynaSquireBookDTO> addSquireFromRecord(DynaSquireRecordDTO dto) {
        DynaSquireDTO squireDTO = new DynaSquireDTO();
        BeanUtils.copyProperties(dto, squireDTO);
        squireDTO.setSquireBusType(SquireCacheConstant.BUSINESS_TYPE_RECORD);
        squireDTO.setBookScore(0);
        // squireDTO.setSelectContent("");
        Integer id = this.add(squireDTO);
        this.addToCache(squireDTO);
        DynaSquireBookDTO result = new DynaSquireBookDTO();
        result.setDynaSquireId(id);
        return new ObjectResponse<>().data(result).rel(true);
    }

    private Integer add(DynaSquireDTO dto) {
        if (null == dto.getSelectContent()) {
            dto.setSelectContent("");
        }
        dto.setNumReply(0);
        dto.setNumFavor(0);
        dto.setIsDel(0);
        dto.setIsDisp(dto.getIsDisp() != null ? dto.getIsDisp() : 0);
        dto.setModifyBy(0);
        dto.setModifyTime(BigInteger.ZERO);
        log.info("新增广场信息, dto: {}", dto);
        dynaSquireMapper.add(dto);
        // 统计数量
        SquireEventBusCenter
                .post(new SquireEventDTO().bussinessId(dto.getUserId()).operate(OperateEnum.ADD).squireCount(true));
        return dto.getDynaSquireId();
    }

    /**
     * 添加到缓存，格式为{key, value, score}: {key, id, -id}<br>
     * 对象的具体内容分布到各个缓存中（如果有分片）, key为${prefix}:id<br>
     * 如果随机数大于一个指定值，并且拥有指定的锁，则进行长度截断
     *
     * @param dto
     */
    private void addToCache(DynaSquireDTO dto) {
        try {
            zSetOperations.add(SquireCacheConstant.SQUIRE_LIST_KEY, dto.getDynaSquireId(), -dto.getDynaSquireId());
            valueOperations.set(SquireCacheConstant.SQUIRE_OBJECT_PREFIX + dto.getDynaSquireId(),
                    JSONMapper.json(dto, dtoIgnore), 1, TimeUnit.HOURS);
            if (Math.random() > SquireCacheConstant.CEIL_TO_TRIM_LIST) {
                Optional<String> identifier = redisService.lock(SquireCacheConstant.CEIL_TO_TRIM_LOCK, 3,
                        TimeUnit.SECONDS);
                if (!identifier.isPresent()) {
                    return;
                }
                try {
                    zSetOperations.removeRange(SquireCacheConstant.SQUIRE_LIST_KEY,
                            SquireCacheConstant.SQUIRE_LIST_LENGTH - 1L,
                            zSetOperations.size(SquireCacheConstant.SQUIRE_LIST_KEY));
                } finally {
                    redisService.unlock(SquireCacheConstant.CEIL_TO_TRIM_LOCK, identifier.get());
                }
            }
        } catch (Exception e) {
            // 只做记录，不做处理
            log.error("添加到缓存异常, dto: {}", dto, e);
        }
    }

    @Transactional
    @Override
    public BaseResponse incrementSquireNum(Integer userId, OperateEnum operate) {
        String redisKey = SquireCacheConstant.USER_SQUIRE_NUM_PREFIX + userId;
        if (OperateEnum.ADD == operate) {
            return this.incrementSquireNum(redisKey, userId);
        }
        return this.decreaseSquireNum(redisKey, userId);
    }

    private BaseResponse incrementSquireNum(String redisKey, Integer userId) {
        try {
            Boolean exist = redisTemplate.expire(redisKey, 1L, TimeUnit.HOURS);
            if (exist) {
                valueOperations.increment(redisKey, 1L);
                return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
            }
            // 从数据库加载
            DynaSquireUserStatDTO statDTO = dynaSquireUserStatMapper.findByUserId(userId);
            if (statDTO == null) {
                statDTO = this.getDefaultStatUserInstance(userId);
                try {
                    dynaSquireUserStatMapper.add(statDTO);
                } catch (Exception e) {
                    log.warn("新增统计失败，可能其他线程已经新增, userId: {}", userId, e);
                }
            }
            valueOperations.setIfAbsent(redisKey, statDTO.getNumSquire());
            valueOperations.increment(redisKey, 1L);
            redisTemplate.expire(redisKey, 1L, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error("统计动态数异常, userId: {}", userId, e);
            return BaseResponse.failure(StatusEnum.FAILURE.getDescribe());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    private BaseResponse decreaseSquireNum(String redisKey, Integer userId) {
        try {
            Boolean exist = redisTemplate.expire(redisKey, 1L, TimeUnit.HOURS);
            if (exist) {
                valueOperations.increment(redisKey, -1L);
                return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
            }
            // 从数据库加载
            DynaSquireUserStatDTO statDTO = dynaSquireUserStatMapper.findByUserId(userId);
            if (statDTO == null) {
                log.warn("统计信息不存在, userId: {}", userId);
                return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
            }
            valueOperations.setIfAbsent(redisKey, statDTO.getNumSquire());
            valueOperations.increment(redisKey, -1L);
            redisTemplate.expire(redisKey, 1L, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error("统计动态数异常, userId: {}", userId, e);
            return BaseResponse.failure(StatusEnum.FAILURE.getDescribe());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    private DynaSquireUserStatDTO getDefaultStatUserInstance(Integer userId) {
        DynaSquireUserStatDTO statDTO = new DynaSquireUserStatDTO();
        statDTO.setUserId(userId);
        statDTO.setNumSquire(0);
        statDTO.setCreateBy(0);
        statDTO.setCreateTime(DateUtil.getCurrentTime());
        statDTO.setModifyBy(0);
        statDTO.setModifyTime(BigInteger.ZERO);
        return statDTO;
    }

    @Override
    public BaseResponse initializeSquireNum(Set<Integer> userIdSet) {
        Optional<String> identifier = redisService.lock(SquireCacheConstant.SQUIRE_INIT_LOCK, 5, TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "初始化任务正在执行，请稍后...");
        }
        try {
            if (userIdSet == null || userIdSet.isEmpty()) {
                // 加载所有用户
                List<DynaSquireDTO> list = dynaSquireMapper.findUserIdList();
                userIdSet = Sets.newHashSetWithExpectedSize(list.size());
                for (DynaSquireDTO dto : list) {
                    userIdSet.add(dto.getUserId());
                }
            }
            for (Integer userId : userIdSet) {
                Long count = dynaSquireMapper.countUserSquire(userId);
                String redisKey = SquireCacheConstant.USER_SQUIRE_NUM_PREFIX + userId;
                // 从数据库加载
                DynaSquireUserStatDTO statDTO = dynaSquireUserStatMapper.findByUserId(userId);
                if (statDTO == null) {
                    statDTO = this.getDefaultStatUserInstance(userId);
                    statDTO.setNumSquire(count == null ? 0 : count.intValue());
                    try {
                        dynaSquireUserStatMapper.add(statDTO);
                    } catch (Exception e) {
                        log.warn("新增统计失败，可能其他线程已经新增, userId: {}", userId, e);
                    }
                } else {
                    statDTO.setNumSquire(count == null ? 0 : count.intValue());
                    dynaSquireUserStatMapper.update(statDTO);
                }
                valueOperations.set(redisKey, statDTO.getNumSquire(), 1, TimeUnit.HOURS);
            }
        } finally {
            redisService.unlock(SquireCacheConstant.SQUIRE_INIT_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public BaseResponse incrementFavorNum(Integer dynaSquireId, OperateEnum operate) {
        String redisKey = SquireCacheConstant.SQUIRE_FAVOR_NUM_PREFIX + dynaSquireId;
        try {
            Boolean exist = redisTemplate.expire(redisKey, 1L, TimeUnit.HOURS);
            if (exist) {
                valueOperations.increment(redisKey, OperateEnum.ADD == operate ? 1L : -1L);
                return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
            }
            // 从数据库加载
            DynaSquireDTO squireDTO = dynaSquireMapper.findById(dynaSquireId);
            if (squireDTO == null) {
                log.warn("动态不存在, dynaSquireId: {}", dynaSquireId);
                return BaseResponse.failure("动态不存在");
            }
            valueOperations.setIfAbsent(redisKey, squireDTO.getNumFavor());
            valueOperations.increment(redisKey, OperateEnum.ADD == operate ? 1L : -1L);
            redisTemplate.expire(redisKey, 1L, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error("统计动态数异常, dynaSquireId: {}, operate: {}", dynaSquireId, operate, e);
            return BaseResponse.failure(StatusEnum.FAILURE.getDescribe());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public BaseResponse initializeFavorNum(Set<Integer> dynaSquireIdSet) {
        Optional<String> identifier = redisService.lock(SquireCacheConstant.SQUIRE_INIT_LOCK, 5, TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "初始化任务正在执行，请稍后...");
        }
        try {
            log.info("开始初始化, dynaSquireIdSet: {}", dynaSquireIdSet);
            if (dynaSquireIdSet != null && !dynaSquireIdSet.isEmpty()) {
                List<DynaSquireDTO> list = dynaSquireMapper.batchGetSquireById(dynaSquireIdSet);
                this.initializeFavorNumBySquireList(list);
                return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
            }
            // 初始化所有
            Integer lastId = null;
            List<DynaSquireDTO> list = dynaSquireMapper.findSquireByLastId(lastId, 0, 1000);
            while (!list.isEmpty()) {
                this.initializeFavorNumBySquireList(list);
                lastId = list.get(list.size() - 1).getDynaSquireId();
                list = dynaSquireMapper.findSquireByLastId(lastId, 0, 1000);
            }
        } finally {
            redisService.unlock(SquireCacheConstant.SQUIRE_INIT_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    /**
     * @param dynaSquireList
     * @return
     */
    private void initializeFavorNumBySquireList(List<DynaSquireDTO> dynaSquireList) {
        for (DynaSquireDTO dto : dynaSquireList) {
            Long count = dynaSquireFavorService.countFavorBySquire(dto.getDynaSquireId());
            String redisKey = SquireCacheConstant.SQUIRE_FAVOR_NUM_PREFIX + dto.getDynaSquireId();
            dto.setNumFavor(count == null ? 0 : count.intValue());
            dynaSquireMapper.updateFavorNum(dto);
            Boolean hasKey = redisTemplate.hasKey(redisKey);
            if (hasKey) {
                valueOperations.set(redisKey, dto.getNumFavor(), 1, TimeUnit.HOURS);
            }
        }
    }

    @Override
    public BaseResponse incrementCommentNum(Integer dynaSquireId, OperateEnum operate) {
        String redisKey = SquireCacheConstant.SQUIRE_COMMENT_NUM_PREFIX + dynaSquireId;
        try {
            Boolean exist = redisTemplate.expire(redisKey, 1L, TimeUnit.HOURS);
            if (exist) {
                valueOperations.increment(redisKey, OperateEnum.ADD == operate ? 1L : -1L);
                return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
            }
            // 从数据库加载
            DynaSquireDTO squireDTO = dynaSquireMapper.findById(dynaSquireId);
            if (squireDTO == null) {
                log.warn("动态不存在, dynaSquireId: {}", dynaSquireId);
                return BaseResponse.failure("动态不存在");
            }
            valueOperations.setIfAbsent(redisKey, squireDTO.getNumReply());
            valueOperations.increment(redisKey, OperateEnum.ADD == operate ? 1L : -1L);
            redisTemplate.expire(redisKey, 1L, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error("统计动态数异常, dynaSquireId: {}, operate: {}", dynaSquireId, operate, e);
            return BaseResponse.failure(StatusEnum.FAILURE.getDescribe());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public BaseResponse initializeCommentNum(Set<Integer> dynaSquireIdSet) {
        Optional<String> identifier = redisService.lock(SquireCacheConstant.SQUIRE_COMMENT_LOCK_PREFIX, 5,
                TimeUnit.MINUTES);
        if (!identifier.isPresent()) {
            return BaseResponse.failure(StatusEnum.ILLEGAL_STATE.getStatus(), "初始化任务正在执行，请稍后...");
        }
        try {
            log.info("开始初始化, dynaSquireIdSet: {}", dynaSquireIdSet);
            if (dynaSquireIdSet != null && !dynaSquireIdSet.isEmpty()) {
                List<DynaSquireDTO> list = dynaSquireMapper.batchGetSquireById(dynaSquireIdSet);
                this.initializeCommentNumBySquireList(list);
                return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
            }
            // 初始化所有
            Integer lastId = null;
            List<DynaSquireDTO> list = dynaSquireMapper.findSquireByLastId(lastId, 0, 1000);
            while (!list.isEmpty()) {
                this.initializeCommentNumBySquireList(list);
                lastId = list.get(list.size() - 1).getDynaSquireId();
                list = dynaSquireMapper.findSquireByLastId(lastId, 0, 1000);
            }
        } finally {
            redisService.unlock(SquireCacheConstant.SQUIRE_INIT_LOCK, identifier.get());
        }
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    /**
     * @param dynaSquireList
     * @return
     */
    private void initializeCommentNumBySquireList(List<DynaSquireDTO> dynaSquireList) {
        for (DynaSquireDTO dto : dynaSquireList) {
            Long count = dynaSquireCommentAppService.countCommentBySquire(dto.getDynaSquireId());
            String redisKey = SquireCacheConstant.SQUIRE_COMMENT_NUM_PREFIX + dto.getDynaSquireId();
            dto.setNumReply(count == null ? 0 : count.intValue());
            dynaSquireMapper.updateCommentNum(dto);
            Boolean hasKey = redisTemplate.hasKey(redisKey);
            if (hasKey) {
                valueOperations.set(redisKey, dto.getNumReply(), 1, TimeUnit.HOURS);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchUpdateCommentNum(List<DynaSquireDO> list) {
        dynaSquireMapper.batchUpdateCommentNum(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchUpdateFavorNum(List<DynaSquireDO> list) {
        dynaSquireMapper.batchUpdateFavorNum(list);
    }
}
