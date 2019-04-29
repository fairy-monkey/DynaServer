package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentFavorDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicFavorAppService;
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
 * @author 郭明毅 guomy 创建时间:2018/9/18 13:55
 */
@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题点赞表相关接口}")
@RestController
@RequestMapping("/facade/dyna/topic/favor/app")
public class DynaTopicFavorAppController {
    @Autowired
    private IDynaTopicFavorAppService dynaFavorAppService;

    /**
     * 点赞
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/doFavor")
    public ObjectResponse<Boolean> doFavor(@RequestBody DynaTopicCommentFavorDTO dto) {
        return dynaFavorAppService.doFavor(dto);
    }
}
