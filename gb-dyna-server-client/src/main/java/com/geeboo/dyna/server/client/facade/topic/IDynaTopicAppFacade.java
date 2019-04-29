package com.geeboo.dyna.server.client.facade.topic;

import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicListAndUserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Title: 提供给app相关的接口<br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/12 10:59
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaTopicAppFacade {
    /**
     * 话题分页
     *
     * @param page 分页大小不能大于100
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topic/app/getTopicPage")
    ObjectResponse<DynaTopicListAndUserDTO> getTopicPage(@RequestBody PageRestRequest<DynaTopicDTO> page);

    /**
     * 查询找书首页话题
     *
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topic/app/findLatestTopic")
    ObjectResponse<DynaTopicDTO> findLatestTopic();
}
