package com.geeboo.dyna.server.service.digest;


import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestFavorDTO;

import java.util.Set;

public interface IDynaDigestFavorAppService {
    /**
     * @description：书摘点赞
     * @author：luozh
     * @date：2018/9/28 15:57
     */
    BaseResponse doDigestFavor(DynaDigestFavorDTO dto);

    Set<Integer> getDigestIdsByUserId(DynaDigestFavorDTO dto);
}