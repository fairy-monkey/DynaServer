package com.geeboo.dyna.server.service.impl.book;

import com.geeboo.common.bean.BeanUtils;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.context.BaseContextHandler;
import com.geeboo.common.exception.runtime.ServiceException;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.*;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireReadBookDTO;
import com.geeboo.dyna.server.config.DynaServerConfig;
import com.geeboo.dyna.server.constant.BookCacheConstant;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.mapper.book.IDynaBookCommentMapper;
import com.geeboo.dyna.server.mapper.book.IDynaBookStatMapper;
import com.geeboo.dyna.server.service.book.IDynaBookCommentAppService;
import com.geeboo.dyna.server.service.book.IDynaBookCommentReplyAppService;
import com.geeboo.dyna.server.service.book.IDynaBookFavorAppService;
import com.geeboo.dyna.server.service.squire.IDynaSquireWriteService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 22:31
 */
@Slf4j
@Service
public class DynaBookCommentAppServiceImpl implements IDynaBookCommentAppService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private IDynaBookFavorAppService dynaBookFavorAppService;
    @Autowired
    private IDynaSquireWriteService dynaSquireWriteService;
    @Autowired
    private IDynaBookCommentReplyAppService dynaBookCommentReplyAppService;
    @Autowired
    private IDynaBookCommentMapper dynaBookCommentMapper;
    @Autowired
    private IDynaBookStatMapper dynaBookStatMapper;
    @Autowired
    private DynaServerConfig dynaServerConfig;

    @Override
    public TableResultResponse<DynaBookCommentListDTO> getCommentPage(PageRestRequest<DynaBookCommentListDTO> page) {
        Integer specialId = dynaServerConfig.getDynaSpecialId();
        Integer dynaBookCommentId = page.getData().getDynaBookCommentId();
        Integer bookUserId = page.getData().getBookUserId();
        Integer userId = page.getData().getUserId();
        Integer pageSize = page.getPage().getPageSize();
        List<DynaBookCommentListDTO> list;
        if (dynaBookCommentId == null || dynaBookCommentId.equals(0)) {
            list = this.getWithSpecialList(page, specialId);
        } else {
            list = dynaBookCommentMapper.getCommentByBookUserAndLastId(bookUserId, dynaBookCommentId, specialId, userId,
                        pageSize);
        }
        this.setCommentInfo(list, page.getData());
        TableResultResponse response = new TableResultResponse<>(0, 0, list);
        return response;
    }

    /**
     * 获取特殊列表，例如书大痴回复
     *
     * @param page
     * @return
     */
    private List<DynaBookCommentListDTO> getWithSpecialList(PageRestRequest<DynaBookCommentListDTO> page,
                Integer specialId) {
        List<DynaBookCommentListDTO> specialList;
        Integer bookUserId = page.getData().getBookUserId();
        Integer pageSize = page.getPage().getPageSize();
        Integer userId = page.getData().getUserId();
        if (specialId != null) {
            specialList = dynaBookCommentMapper.getCommentByBookUserAndSpecialId(bookUserId, specialId, pageSize);
        } else {
            specialList = Lists.newArrayList();
        }
        int remainPageSize = page.getPage().getPageSize() - specialList.size();
        Integer dynaBookCommentId = page.getData().getDynaBookCommentId();
        List<DynaBookCommentListDTO> list = dynaBookCommentMapper.getCommentByBookUserAndLastId(bookUserId,
                    dynaBookCommentId, specialId, userId, remainPageSize);  //这边原先把用户直接写成0了
        specialList.addAll(list);
        return specialList;
    }

    private void setCommentInfo(List<DynaBookCommentListDTO> list, DynaBookCommentListDTO queryDTO) {
        if (list.isEmpty()) {
            return;
        }
        Set<Integer> commentIdSet = Sets.newHashSetWithExpectedSize(list.size());
        for (DynaBookCommentListDTO dto : list) {
            commentIdSet.add(dto.getDynaBookCommentId());
        }
        this.setNumIfHasCache(list, commentIdSet);
        this.setUserFavor(list, queryDTO, commentIdSet);
    }

    private void setNumIfHasCache(List<DynaBookCommentListDTO> list, Set<Integer> commentIdSet) {
        List<String> replyKeyList = Lists.newArrayListWithExpectedSize(commentIdSet.size());
        List<String> favorKeyList = Lists.newArrayListWithExpectedSize(commentIdSet.size());
        for (DynaBookCommentListDTO dto : list) {
            replyKeyList.add(BookCacheConstant.BOOK_COMMENT_REPLY_NUM + dto.getDynaBookCommentId());
            favorKeyList.add(BookCacheConstant.BOOK_COMMENT_FAVOR_NUM + dto.getDynaBookCommentId());
        }
        List<Integer> replyNumList = valueOperations.multiGet(replyKeyList);
        List<Integer> favorNumList = valueOperations.multiGet(favorKeyList);
        // 如果存在于缓存中，则设置为缓存中的数量
        int index = 0;
        for (DynaBookCommentListDTO dto : list) {
            Integer replyNum = replyNumList.get(index);
            if (replyNum != null) {
                dto.setNumReply(replyNum);
            }
            Integer favorNum = favorNumList.get(index);
            if (favorNum != null) {
                dto.setNumFavor(favorNum);
            }
            index++;
        }
    }

    private void setUserFavor(List<DynaBookCommentListDTO> list, DynaBookCommentListDTO queryDTO,
                Set<Integer> commentIdSet) {
        if (queryDTO == null || queryDTO.getUserId() == null || queryDTO.getUserId().equals(0)) {
            return;
        }
        if (commentIdSet.isEmpty()) {
            return;
        }
        Set<Integer> commentIdHasFavorSet = dynaBookFavorAppService.findCommentFavorListByUser(commentIdSet,
                    queryDTO.getUserId());
        for (DynaBookCommentListDTO dto : list) {
            if (commentIdHasFavorSet.contains(dto.getDynaBookCommentId())) {
                dto.setHasFavor(true);
            } else {
                dto.setHasFavor(false);
            }
        }
    }

    @Override
    public DynaBookCommentListDTO getCommentDetail(Integer id) {
        DynaBookCommentListDTO dto = dynaBookCommentMapper.getCommentDetail(id);
        if (dto == null) {
            return dto;
        }
        List<DynaBookCommentListDTO> list = Lists.newArrayList(dto);
        Set<Integer> commentIdSet = Sets.newHashSet(dto.getDynaBookCommentId());
        // 这边主要是用来判断当前用户有没有点赞，这里的用户应该登录的用户，而不是评论的这个用户，所以不能跟原来的方法一样，原来读取的就是登录的用户，这边是评论详情，所以一样的话，拿的是评论的那用户id,不是登录用户的id
        this.setUserCurrentFavor(list, dto, commentIdSet);
        return dto;
    }

    private void setUserCurrentFavor(List<DynaBookCommentListDTO> list, DynaBookCommentListDTO queryDTO,
                Set<Integer> commentIdSet) {
        if (queryDTO == null || queryDTO.getUserId() == null || queryDTO.getUserId().equals(0)) {
            return;
        }
        if (commentIdSet.isEmpty()) {
            return;
        }
        Integer userId = BaseContextHandler.getUserID();
        Set<Integer> commentIdHasFavorSet = dynaBookFavorAppService.findCommentFavorListByUser(commentIdSet, userId);
        for (DynaBookCommentListDTO dto : list) {
            if (commentIdHasFavorSet.contains(dto.getDynaBookCommentId())) {
                dto.setHasFavor(true);
            } else {
                dto.setHasFavor(false);
            }
        }
    }

    @Override
    public List<DynaBookCommentDTO> getRecentCommentUserList(Integer dynaBookId) {
        return dynaBookCommentMapper.getRecentCommentUserList(dynaBookId);
    }

    @Override
    public long getCommentCountGroupByAccount(Integer dynaBookId) {
        Long count = dynaBookCommentMapper.getCommentCountGroupByAccount(dynaBookId);
        return count != null ? count.longValue() : 0;
    }

    @Transactional
    @Override
    public ObjectResponse<DynaBookCommentDTO> addComment(DynaBookCommentDTO dto) {
        dto.setNumFavor(0);
        dto.setNumReply(0);
        dto.setIsDel(0);
        dto.setIndexNo(0);
        dynaBookCommentMapper.add(dto);
        log.info("新增评论: dto: {}", dto);
        this.incrementCommentCount(dto, OperateEnum.ADD);
        DynaBookCommentDTO resultDTO = new DynaBookCommentDTO();
        resultDTO.setDynaBookCommentId(dto.getDynaBookCommentId());
        return new ObjectResponse<>().data(resultDTO).rel(true);
    }

    @Transactional
    @Override
    public ObjectResponse<DynaSquireReadBookDTO> addReadComment(DynaSquireReadBookDTO dto) {
        log.info("新增读后感: dto: {}", dto);
        DynaBookCommentDTO commentDTO = new DynaBookCommentDTO();
        BeanUtils.copyIgnoreNull(dto, commentDTO);
        commentDTO.setUserId(dto.getUserId());
        commentDTO.setBookScore(dto.getBookScore().byteValue());
        commentDTO.setModifyBy(0);
        commentDTO.setModifyTime(BigInteger.ZERO);
        ObjectResponse<DynaBookCommentDTO> bookCommentResponse = this.addComment(commentDTO);
        // 读后感直接发布广场
        ObjectResponse<DynaSquireReadBookDTO> squireResponse = dynaSquireWriteService.addSquireFromReadBook(dto);
        DynaSquireReadBookDTO resultDTO = new DynaSquireReadBookDTO();
        resultDTO.setDynaBookCommentId(bookCommentResponse.getData().getDynaBookCommentId());
        resultDTO.setDynaSquireId(squireResponse.getData().getDynaSquireId());
        return new ObjectResponse<>().data(resultDTO).rel(true);
    }

    @Transactional
    @Override
    public BaseResponse deleteComment(DynaBookCommentDTO dto) {
        DynaBookCommentDTO oldDTO = dynaBookCommentMapper.findById(dto.getDynaBookCommentId());
        if (oldDTO == null) {
            log.info("评论不存在，删除成功, dto: {}", dto);
            return BaseResponse.success("删除成功");
        }
        if (!oldDTO.getUserId().equals(dto.getUserId())) {
            return BaseResponse.failure(StatusEnum.NO_ENTRY.getStatus(), "只能删除自己发表的评论");
        }
        dynaBookCommentMapper.deleteDynaBookComment(dto.getDynaBookCommentId());
        Long replyCount = dynaBookCommentReplyAppService.countReplyByComment(dto.getDynaBookCommentId());
        log.info("删除评论: dto: {}, replyCount: {}", dto, replyCount);
        this.incrementCommentCount(dto, OperateEnum.DELETE);
        return BaseResponse.success("删除成功");
    }

    @Override
    public void incrementCommentCount(DynaBookCommentReplyDTO dto, OperateEnum operate) throws ServiceException {
        String redisKey = BookCacheConstant.BOOK_COMMENT_REPLY_NUM + dto.getDynaBookCommentId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaBookCommentDTO commentDTO = dynaBookCommentMapper.findById(dto.getDynaBookCommentId());
        if (commentDTO != null) {
            valueOperations.setIfAbsent(redisKey, commentDTO.getNumReply());
            redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
            this.incrementByRedisKey(redisKey, operate);
        }
    }

    @Override
    public void incrementCommentCount(DynaBookCommentFavorDTO dto, OperateEnum operate) throws ServiceException {
        String redisKey = BookCacheConstant.BOOK_COMMENT_FAVOR_NUM + dto.getDynaBookCommentId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaBookCommentDTO commentDTO = dynaBookCommentMapper.findById(dto.getDynaBookCommentId());
        valueOperations.setIfAbsent(redisKey, commentDTO.getNumFavor());
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        this.incrementByRedisKey(redisKey, operate);
    }

    @Override
    public void incrementCommentCount(DynaBookCommentDTO dto, OperateEnum operate) throws ServiceException {
        String redisKey = BookCacheConstant.BOOK_COMMENT_NUM + dto.getBookUserId();
        Boolean flag = redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        if (flag) {
            this.incrementByRedisKey(redisKey, operate);
            return;
        }
        // 从数据库更新
        DynaBookStatDTO statDTO = dynaBookStatMapper.findByBookUserId(dto.getBookUserId());
        if (statDTO == null) {
            statDTO = new DynaBookStatDTO();
            statDTO.setNumComment(0);
        }
        valueOperations.setIfAbsent(redisKey, statDTO.getNumComment());
        redisTemplate.expire(redisKey, 1, TimeUnit.DAYS);
        this.incrementByRedisKey(redisKey, operate);
    }

    @Override
    public ObjectResponse<DynaBookStatDTO> getBookStat(Integer bookUserId) {
        ObjectResponse<DynaBookStatDTO> response = new ObjectResponse<>();
        String redisKey = BookCacheConstant.BOOK_COMMENT_NUM + bookUserId;
        Integer value = (Integer) valueOperations.get(redisKey);
        if (value != null) {
            DynaBookStatDTO statDTO = new DynaBookStatDTO();
            statDTO.setNumComment(value);
            response.setData(statDTO);
            return response;
        }
        DynaBookStatDTO stat = dynaBookStatMapper.findByBookUserId(bookUserId);
        response.setData(stat);
        return response;
    }

    private void incrementByRedisKey(String redisKey, OperateEnum operate) {
        if (operate == OperateEnum.ADD) {
            valueOperations.increment(redisKey, 1L);
        } else {
            valueOperations.increment(redisKey, -1L);
        }
    }
}
