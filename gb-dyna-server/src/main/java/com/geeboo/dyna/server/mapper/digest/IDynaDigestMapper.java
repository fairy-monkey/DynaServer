package com.geeboo.dyna.server.mapper.digest;

import com.geeboo.dyna.server.client.dto.digest.DynaDigestDTO;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestListDTO;
import com.geeboo.dyna.server.entity.digest.DynaDigestDO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaDigestMapper extends Mapper<DynaDigestDO> {
    DynaDigestDTO findById(Integer dynaDigestId);

    int add(DynaDigestDTO dto);

    int update(DynaDigestDTO dto);

    int remove(Integer dynaDigestId);

    List<DynaDigestDTO> query(DynaDigestDTO dto);

    DynaDigestDTO findByCondition(DynaDigestDTO dto);

    Page<DynaDigestListDTO> getDigestPage(DynaDigestListDTO dto);

    int incrementNumSupport(@Param(value = "dynaDigestId") Integer dynaDigestId, @Param(value = "isFavor") Integer isFavor);
}