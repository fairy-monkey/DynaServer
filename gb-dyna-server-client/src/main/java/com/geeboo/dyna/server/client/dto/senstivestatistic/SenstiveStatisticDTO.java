package com.geeboo.dyna.server.client.dto.senstivestatistic;

import com.geeboo.common.page.AbstractRequestPage;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tangwei
 * @Title: SenstiveStatisticDTO
 * @ProjectName DynaServer
 * @Description: 敏感词统计通用传输对象
 * @date 2018/11/30 16:14
 */
@Data
public class SenstiveStatisticDTO extends AbstractRequestPage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tableName; // 表名
    private String fields; // 所需要查询的字段列表
    private long createTime; // 需要过滤的时间条件
    private String prikeyName; // 主键名称
    private Integer prikeyVaule; // 主键值
}
