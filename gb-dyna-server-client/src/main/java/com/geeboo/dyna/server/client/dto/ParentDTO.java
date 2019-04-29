package com.geeboo.dyna.server.client.dto;

import lombok.Data;

/**
 * @author tangwei
 * @Title: ParentDTO
 * @ProjectName DynaServer
 * @Description: 评论以及回复相关父类DTO 敏感词
 * @date 2018/11/21 15:07
 */
@Data
public class ParentDTO {
    private Integer isSensitive = 0; // 是否包含敏感词(1是,0否,默认为0
}
