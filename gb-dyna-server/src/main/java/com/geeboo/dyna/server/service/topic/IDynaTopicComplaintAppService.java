package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicComplaintDTO;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/19 11:03
 */
public interface IDynaTopicComplaintAppService {
    /**
     * 举报评论
     *
     * @param dto
     * @return
     */
    BaseResponse complaint(DynaTopicComplaintDTO dto);
}