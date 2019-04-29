package com.geeboo.dyna.server.mapper.squire;

import com.geeboo.dyna.server.client.dto.squire.DynaSquireUserStatDTO;
import com.geeboo.dyna.server.entity.squire.DynaSquireUserStatDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaSquireUserStatMapper extends Mapper<DynaSquireUserStatDO> {
    DynaSquireUserStatDTO findById(Integer dynaSquireUserStatId);

    int add(DynaSquireUserStatDTO dto);

    int update(DynaSquireUserStatDTO dto);

    int deleteDynaSquireUserStat(Integer id);

    List<DynaSquireUserStatDTO> query(DynaSquireUserStatDTO dto);

    DynaSquireUserStatDTO findByCondition(DynaSquireUserStatDTO dto);

    /**
     * 根据用户ID查找
     *
     * @param userId
     * @return
     */
    DynaSquireUserStatDTO findByUserId(@Param("userId") Integer userId);

    /**
     * 批量更新用户动态统计数
     *
     * @param list
     * @return
     */
    void batchUpdateUserStat(@Param("list") List<DynaSquireUserStatDO> list);
}


