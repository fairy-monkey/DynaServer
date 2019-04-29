package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorRequestDTO;

import java.util.Set;

public interface IDynaSquireFavorService {
    /**
     * 点赞
     *
     * @param dto
     * @return 是否是新增的且为点赞
     */
    ObjectResponse<Boolean> doFavor(DynaSquireFavorRequestDTO dto);

    /**
     * 从广场ID集合中查找出用户点赞的广场ID
     *
     * @param squireIdSet
     * @param userId
     * @return
     */
    Set<Integer> findSquireFavorListByUser(Set<Integer> squireIdSet, Integer userId);

    /**
     * 从广场ID集合中查找出用户点赞的广场ID
     *
     * @param squireIdSet
     * @param userId
     * @return
     * @see #findSquireFavorListByUser
     */
    ObjectResponse<Set<Integer>> findSquireFavorList(Set<Integer> squireIdSet, Integer userId);

    /**
     * 统计点赞数量
     *
     * @param squireId
     * @return
     */
    Long countFavorBySquire(Integer squireId);

    ObjectResponse<DynaSquireFavorDTO> getDynaSquireFavorDTO(DynaSquireFavorDTO dto);
}


