package com.geeboo.dyna.server.client.facade.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicComplaintDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/20 10:40
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaTopicComplaintAppFacade {
    /**
     * 举报评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topic/complaint/app/complaint")
    BaseResponse complaint(@RequestBody DynaTopicComplaintDTO dto);
}