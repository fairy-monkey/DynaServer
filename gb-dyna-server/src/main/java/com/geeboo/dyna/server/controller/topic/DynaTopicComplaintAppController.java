package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicComplaintDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicComplaintAppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/20 10:48
 */
@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/topic/complaint/app")
public class DynaTopicComplaintAppController {
    @Autowired
    private IDynaTopicComplaintAppService dynaTopicComplaintAppService;

    @PostMapping(value = "/complaint")
    public BaseResponse complaint(@RequestBody DynaTopicComplaintDTO dto) {
        return dynaTopicComplaintAppService.complaint(dto);
    }

}
