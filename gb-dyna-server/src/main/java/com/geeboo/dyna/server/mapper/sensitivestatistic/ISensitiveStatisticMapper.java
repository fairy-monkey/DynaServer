package com.geeboo.dyna.server.mapper.sensitivestatistic;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author tangwei
 * @Title: SensitiveStatisticMapper
 * @ProjectName DynaServer
 * @Description:
 * @date 2018/11/30 15:59
 */
public interface ISensitiveStatisticMapper {
    List<Map<String, Object>> getList(@Param("tableName") String tableName, @Param("fields") String fields,
                @Param("createTime") long createTime, @Param("startRow") int startRow, @Param("endRow") int endRow);

    List<Map<String, Object>> getNoSensitiveList(@Param("tableName") String tableName, @Param("fields") String fields,
                @Param("startRow") int startRow, @Param("endRow") int endRow);

    int getCount(@Param("tableName") String tableName, @Param("createTime") long createTime);

    int getNoSensitiveCount(@Param("tableName") String tableName);

    int update(@Param("tableName") String tableName, @Param("prikeyIdName") String prikeyIdName,
                @Param("prikeyIdValue") int prikeyIdValue);
}
