package com.geeboo.dyna.server.mapper.squire;

import com.geeboo.dyna.server.client.dto.squire.DynaSquireStatDTO;
import com.geeboo.dyna.server.entity.squire.DynaSquireStatDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaSquireStatMapper extends Mapper<DynaSquireStatDO> {
    DynaSquireStatDTO findById(Integer dynaSquireStatId);

    int add(DynaSquireStatDTO dto);

    int update(DynaSquireStatDTO dto);

    int deleteDynaSquireStat(Integer id);

    List<DynaSquireStatDTO> query(DynaSquireStatDTO dto);

    DynaSquireStatDTO findByCondition(DynaSquireStatDTO dto);

    DynaSquireStatDTO findBySquireId(Integer dynaSquireId);

    List<DynaSquireStatDTO> batchGetStatByBookUserId(@Param("squireIdSet") Set<Integer> squireIdSet);
}


