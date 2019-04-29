package com.geeboo.dyna.server.service.impl.squire;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.common.qiniu.QiniuConfiguration;
import com.geeboo.common.util.JSONMapper;
import com.geeboo.dyna.server.client.dto.squire.DynaAttentionSquireDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireUserStatDTO;
import com.geeboo.dyna.server.constant.SquireCacheConstant;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireFavorMapper;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireMapper;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireUserStatMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireReadService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
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
public class DynaSquireReadServiceImpl implements IDynaSquireReadService {
    /**
     * 切勿修改
     */
    private static String[] dtoIgnore = new String[]{"numSupport", "numReply", "isDisp", "isDel", "modifyTime", "modifyBy"};
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ZSetOperations zSetOperations;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaSquireMapper dynaSquireMapper;
    @Autowired
    private IDynaSquireUserStatMapper dynaSquireUserStatMapper;
    @Autowired
    private IDynaSquireFavorMapper dynaSquireFavorMapper;
    @Autowired
    private QiniuConfiguration qiniuConfiguration;

    @Override
    public TableResultResponse<DynaSquireDTO> page(DynaSquireDTO dto, Page<DynaSquireDTO> page) {
        int lastId = dto == null || dto.getDynaSquireId() == null ? 0 : dto.getDynaSquireId();
        Set<Integer> idSet = zSetOperations.rangeByLex(SquireCacheConstant.SQUIRE_LIST_KEY,
                RedisZSetCommands.Range.range().lt(lastId),
                RedisZSetCommands.Limit.limit().count(page.getPageSize()));
        List<DynaSquireDTO> redisList = this.getListFromRedis(idSet);
        /**
         * 1、缓存条数大于每页条数
         */
        if (idSet.size() >= page.getPageSize()) {
            return new TableResultResponse<>(0, 0, redisList);
        }
        /**
         * 2、缓存条数小于每页条数
         */
        // 获取缓存中最后一个dynaSquireId
        Integer lastIdByCache = redisList.isEmpty()
                ? dto.getDynaSquireId()
                : redisList.get(redisList.size() - 1).getDynaSquireId();
        // 剩余部分直接到数据库找
        List<DynaSquireDTO> dbList = this.getListFromDb(lastIdByCache, dto.getUserId(),
                page.getPageSize() - idSet.size());
        if (redisList.isEmpty()) {
            redisList = dbList;
        } else {
            redisList.addAll(dbList);
        }
        this.setCountNum(redisList);
        return new TableResultResponse<>(0, 0, redisList);
    }

    /**
     * 从缓存查找，无论如何始终保持排序 如果缓存中不存在实体，则从数据库加载，并缓存实体
     *
     * @param idSet
     * @return
     */
    private List<DynaSquireDTO> getListFromRedis(Set<Integer> idSet) {
        if (idSet.isEmpty()) {
            return Lists.newArrayListWithExpectedSize(0);
        }
        List<String> keyList = Lists.newArrayListWithExpectedSize(idSet.size());
        for (Integer id : idSet) {
            keyList.add(SquireCacheConstant.SQUIRE_OBJECT_PREFIX + id);
        }
        List<String> redisJsonList = valueOperations.multiGet(keyList);
        List<DynaSquireDTO> redisList = Lists.newArrayListWithExpectedSize(idSet.size());
        // 检查是否有值对象不存在缓存的情况
        Set<Integer> notInCacheIdSet = Sets.newHashSet();
        Map<Integer, Integer> notInCacheIndexMap = Maps.newHashMap();
        int index = 0;
        for (Integer id : idSet) {
            String redisJson = redisJsonList.get(index);
            if (redisJson == null) {
                notInCacheIdSet.add(id);
                notInCacheIndexMap.put(id, index);
                redisList.add(null);
            } else {
                DynaSquireDTO dto = JSONMapper.binding(redisJson, DynaSquireDTO.class);
                redisList.add(dto);
            }
            index++;
        }
        if (notInCacheIdSet.isEmpty()) {
            return redisList;
        }
        // 从数据库查询
        List<DynaSquireDTO> dbList = dynaSquireMapper.batchGetSquireById(notInCacheIdSet);
        for (DynaSquireDTO dto : dbList) {
            Integer id = dto.getDynaSquireId();
            Integer indexInRedisList = notInCacheIndexMap.get(id);
            redisList.set(indexInRedisList, dto);
            valueOperations.setIfAbsent(SquireCacheConstant.SQUIRE_OBJECT_PREFIX + id, JSONMapper.json(dto, dtoIgnore));
            redisTemplate.expire(SquireCacheConstant.SQUIRE_OBJECT_PREFIX + id, 1, TimeUnit.HOURS);
        }
        // 封装七牛域名
        joinQiniu(redisList);
        if (dbList.size() >= notInCacheIdSet.size()) {
            return redisList;
        }
        // 有些数据库查不到，这就麻烦了，要清除缓存相应ID，并且从数据库找N条补上
        return redisList;
    }

    /**
     * 从数据库获取
     *
     * @param lastIdByCache
     * @param pageSize
     * @return
     */
    private List<DynaSquireDTO> getListFromDb(Integer lastIdByCache, Integer userId, Integer pageSize) {
        List<DynaSquireDTO> dbList = dynaSquireMapper.findSquireByLastId(lastIdByCache, userId, pageSize);
        joinQiniu(dbList);
        return dbList;
    }

    /**
     * 设置统计数量
     *
     * @param list
     */
    private void setCountNum(List<DynaSquireDTO> list) {
        Set<String> favorRedisKeySet = Sets.newLinkedHashSetWithExpectedSize(list.size());
        Set<String> commentRedisKeySet = Sets.newLinkedHashSetWithExpectedSize(list.size());
        for (DynaSquireDTO dto : list) {
            favorRedisKeySet.add(SquireCacheConstant.SQUIRE_FAVOR_NUM_PREFIX + dto.getDynaSquireId());
            commentRedisKeySet.add(SquireCacheConstant.SQUIRE_COMMENT_NUM_PREFIX + dto.getDynaSquireId());
        }
        List<Integer> favorNumSet = valueOperations.multiGet(favorRedisKeySet);
        List<Integer> commentNumSet = valueOperations.multiGet(commentRedisKeySet);
        Set<Integer> notInCacheIdSet = Sets.newHashSet();
        int index = 0;
        // 广场评论列表
        for (DynaSquireDTO dto : list) {
            // 设置评论数和点赞数
            Integer favorNum = favorNumSet.get(index);
            Integer commentNum = commentNumSet.get(index);
            dto.setNumFavor(favorNum);
            dto.setNumReply(commentNum);
            if (favorNum == null || commentNum == null) {
                notInCacheIdSet.add(dto.getDynaSquireId());
            }
            index++;
        }
        if (notInCacheIdSet.isEmpty()) {
            return;
        }
        // 缓存中不存在的点赞或者回复的，从数据库中查找，再重新设置点赞和回复数
        List<DynaSquireDTO> dbList = dynaSquireMapper.batchGetSquireById(notInCacheIdSet);
        Map<Integer, DynaSquireDTO> dbMap = Maps.newHashMapWithExpectedSize(dbList.size());
        for (DynaSquireDTO dto : dbList) {
            dbMap.put(dto.getDynaSquireId(), dto);
        }
        for (DynaSquireDTO dto : list) {
            if (dto.getNumFavor() == null || dto.getNumReply() == null) {
                DynaSquireDTO dbDTO = dbMap.get(dto.getDynaSquireId());
                if (null == dbDTO) {
                    dto.setNumFavor(dto.getNumFavor() != null ? dto.getNumFavor() : 0);
                    dto.setNumReply(dto.getNumReply() != null ? dto.getNumReply() : 0);
                } else {
                    dto.setNumFavor(dto.getNumFavor() != null ? dto.getNumFavor() : dbDTO.getNumFavor());
                    dto.setNumReply(dto.getNumReply() != null ? dto.getNumReply() : dbDTO.getNumReply());
                }
            }
        }
    }

    @Override
    public TableResultResponse<DynaSquireDTO> attentionPage(DynaAttentionSquireDTO dto,
                                                            Page<DynaAttentionSquireDTO> page) {
        int lastId = dto == null || dto.getDynaSquireId() == null ? 0 : dto.getDynaSquireId();
        Integer pageSize = page.getPageSize();
        Set<Integer> attentionUserIdSet = dto.getAttentionUserIdSet();
        List<DynaSquireDTO> list = dynaSquireMapper.findAttentionSquireByLastId(lastId, pageSize, attentionUserIdSet);
        this.setCountNum(list);
        joinQiniu(list);
        return new TableResultResponse(0, 0, list);
    }

    @Override
    public TableResultResponse<DynaSquireDTO> userPage(DynaSquireDTO dto, Page<DynaSquireDTO> page) {
        int lastId = dto == null || dto.getDynaSquireId() == null ? 0 : dto.getDynaSquireId();
        List<DynaSquireDTO> list = dynaSquireMapper.findUserSquireByLastId(lastId, page.getPageSize(), dto.getUserId());
        this.setCountNum(list);
        joinQiniu(list);
        return new TableResultResponse(0, 0, list);
    }

    @Override
    public ObjectResponse<DynaSquireDTO> findById(Integer id) {
        DynaSquireDTO dto = dynaSquireMapper.findById(id);
        ObjectResponse<DynaSquireDTO> response = new ObjectResponse<>();
        if (dto != null) {
            joinQiniu(dto);
            return response.data(dto).rel(true);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaSquireDTO> findDetailById(Integer id) {
        DynaSquireDTO dto = dynaSquireMapper.findById(id);
        if (dto == null) {
            return ObjectResponse.failure(StatusEnum.NOT_FOUND.getStatus(), StatusEnum.NOT_FOUND.getDescribe());
        }
        joinQiniu(dto);
        List<DynaSquireDTO> list = Lists.newArrayList(dto);
        this.setCountNum(list);
        return new ObjectResponse<>().data(dto).rel(true);
    }

    @Override
    public ObjectResponse<DynaSquireDTO> findSquireDetail(DynaSquireFavorDTO favorDTO) {
        DynaSquireDTO dto = dynaSquireMapper.findById(favorDTO.getDynaSquireId());
        if (dto == null) {
            return ObjectResponse.failure(StatusEnum.NOT_FOUND.getStatus(), StatusEnum.NOT_FOUND.getDescribe());
        }
        joinQiniu(dto);
        Integer userId = favorDTO.getUserId();
        Integer dynaSquireId = favorDTO.getDynaSquireId();
        if (userId > 0 && dynaSquireId > 0) {
            List<DynaSquireFavorDTO> favorList = dynaSquireFavorMapper.findSquireFavorById(favorDTO);
            if (favorList != null && favorList.size() > 0) {
                dto.setHasFavor(true);
            } else {
                dto.setHasFavor(false);
            }
        }
        List<DynaSquireDTO> list = Lists.newArrayList(dto);
        this.setCountNum(list);
        return new ObjectResponse<>().data(dto).rel(true);
    }

    @Override
    public ObjectResponse<DynaSquireDTO> findByCondition(DynaSquireDTO dto) {
        DynaSquireDTO model = dynaSquireMapper.findByCondition(dto);
        if (null != model) {
            joinQiniu(model);
            ObjectResponse<DynaSquireDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<Long> getUserSquireNum(Integer userId) {
        String redisKey = SquireCacheConstant.USER_SQUIRE_NUM_PREFIX + userId;
        Integer value = (Integer) valueOperations.get(redisKey);
        if (value != null) {
            return new ObjectResponse<>().data(value.longValue()).rel(true);
        }
        // 从数据库加载
        DynaSquireUserStatDTO statDTO = dynaSquireUserStatMapper.findByUserId(userId);
        if (statDTO == null) {
            return new ObjectResponse<>().data(0L).rel(true);
        }
        valueOperations.setIfAbsent(redisKey, statDTO.getNumSquire());
        redisTemplate.expire(redisKey, 1, TimeUnit.HOURS);
        return new ObjectResponse<>().data(statDTO.getNumSquire().longValue()).rel(true);
    }

    @Override
    public TableResultResponse<DynaSquireDTO> findByConditionList(DynaSquireDTO dto) {
        List<DynaSquireDTO> list = dynaSquireMapper.query(dto);
        joinQiniu(list);
        TableResultResponse<DynaSquireDTO> response = new TableResultResponse<>(list.size(), list);
        return response;
    }

    /**
     * 封装七牛域名
     */
    private void joinQiniu(DynaSquireDTO dto) {
        dto.setPhoto(qiniuConfiguration.joinDomain(dto.getPhoto()));
        dto.setCoverPath(qiniuConfiguration.joinBookBucketDomain(dto.getCoverPath()));
    }

    private void joinQiniu(List<DynaSquireDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(dto -> joinQiniu(dto));
    }
}
