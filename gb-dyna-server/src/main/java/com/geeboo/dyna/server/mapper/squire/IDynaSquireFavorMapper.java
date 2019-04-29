package com.geeboo.dyna.server.mapper.squire;

import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorDTO;
import com.geeboo.dyna.server.entity.squire.DynaSquireFavorDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaSquireFavorMapper extends Mapper<DynaSquireFavorDO> {
    DynaSquireFavorDTO findById(Integer squireFavorId);

    int add(DynaSquireFavorDTO dto);

    int update(DynaSquireFavorDTO dto);

    int deleteDynaSquireFavor(Integer id);

    List<DynaSquireFavorDTO> query(DynaSquireFavorDTO dto);

    DynaSquireFavorDTO findByCondition(DynaSquireFavorDTO dto);

    /**
     * 获取单个用户点赞
     *
     * @param dynaSquireId
     * @param userId
     * @return
     */
    DynaSquireFavorDTO getFavorBySquireAndUser(@Param(value = "dynaSquireId") Integer dynaSquireId, @Param(value = "userId") Integer userId);

    /**
     * 用户点赞列表
     *
     * @param squireIdSet
     * @param userId
     * @return
     */
    List<DynaSquireFavorDTO> findSquireFavorListByUser(@Param(value = "squireIdSet") Set<Integer> squireIdSet, @Param(value = "userId") Integer userId);

    /**
     * 统计点赞数量
     *
     * @param squireId
     * @return
     */
    Long countFavorBySquire(@Param("squireId") Integer squireId);

    List<DynaSquireFavorDTO> findSquireFavorById(DynaSquireFavorDTO dto);
}


