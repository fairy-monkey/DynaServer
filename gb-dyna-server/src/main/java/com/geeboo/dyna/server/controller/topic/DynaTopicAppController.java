package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicListAndUserDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicAppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/13 17:22
 */
@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/topic/app")
public class DynaTopicAppController {
    @Autowired
    private IDynaTopicAppService dynaTopicAppService;

    @PostMapping(value = "/getTopicPage")
    public ObjectResponse<DynaTopicListAndUserDTO> getTopicPage(@RequestBody PageRestRequest<DynaTopicDTO> page) {
        return dynaTopicAppService.getTopicPage(page);
    }

    /**
     * 查找找书首页话题
     *
     * @return
     */
    @RequestMapping(value = "/findLatestTopic", method = RequestMethod.POST)
    ObjectResponse<DynaTopicDTO> findLatestTopic() {
        return dynaTopicAppService.findLatestTopic();
    }
}
