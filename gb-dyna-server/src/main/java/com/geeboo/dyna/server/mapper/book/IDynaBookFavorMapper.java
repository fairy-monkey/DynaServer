package com.geeboo.dyna.server.mapper.book;

import com.geeboo.dyna.server.client.dto.book.DynaBookFavorDTO;
import com.geeboo.dyna.server.entity.book.DynaBookFavorDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaBookFavorMapper extends Mapper<DynaBookFavorDO> {
    DynaBookFavorDTO findById(Integer dynaBookFavorId);

    int add(DynaBookFavorDTO dto);

    int update(DynaBookFavorDTO dto);

    int deleteDynaBookFavor(Integer id);

    List<DynaBookFavorDTO> query(DynaBookFavorDTO dto);

    DynaBookFavorDTO findByCondition(DynaBookFavorDTO dto);

    /**
     * 获取单个用户点赞
     *
     * @param bookUserId
     * @param dynaBookCommentId
     * @param userId
     * @return
     */
    DynaBookFavorDTO getFavorByCommentAndUser(@Param(value = "bookUserId") Integer bookUserId, @Param(value = "dynaBookCommentId") Integer dynaBookCommentId, @Param(value = "userId") Integer userId);

    /**
     * 用户点赞列表
     *
     * @return
     */
    List<DynaBookFavorDTO> findCommentFavorListByUser(@Param(value = "commentIdSet") Set<Integer> commentIdSet, @Param(value = "userId") Integer userId);
}
