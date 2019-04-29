package com.geeboo.dyna.server.mapper.digest;


import com.geeboo.dyna.server.client.dto.digest.DynaDigestFavorDTO;
import com.geeboo.dyna.server.entity.digest.DynaDigestDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaDigestFavorMapper extends Mapper<DynaDigestDO> {
    DynaDigestFavorDTO getFavorByDigestAndUser(@Param(value = "dynaDigestId") Integer dynaDigestId, @Param(value = "userId") Integer userId);

    int add(DynaDigestFavorDTO dto);

    int update(DynaDigestFavorDTO dto);

    List<DynaDigestFavorDTO> getDigestFavorList(Integer userId);
}