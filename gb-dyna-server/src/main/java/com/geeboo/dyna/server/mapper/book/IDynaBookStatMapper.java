package com.geeboo.dyna.server.mapper.book;

import com.geeboo.dyna.server.client.dto.book.DynaBookStatDTO;
import com.geeboo.dyna.server.entity.book.DynaBookStatDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaBookStatMapper extends Mapper<DynaBookStatDO> {
    DynaBookStatDTO findById(Integer dynaBookStatId);

    int add(DynaBookStatDTO dto);

    int update(DynaBookStatDTO dto);

    int deleteDynaBookStat(Integer id);

    List<DynaBookStatDTO> query(DynaBookStatDTO dto);

    DynaBookStatDTO findByCondition(DynaBookStatDTO dto);

    DynaBookStatDTO findByBookUserId(Integer bookUserId);

    List<DynaBookStatDTO> batchGetStatByBookUserId(@Param("bookUserIdSet") Set<Integer> bookUserIdSet);

    void batchSaveOrUpdateCommentNum(@Param("list") List<DynaBookStatDO> list);
}
