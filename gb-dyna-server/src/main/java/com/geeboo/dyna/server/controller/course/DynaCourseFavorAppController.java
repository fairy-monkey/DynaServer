package com.geeboo.dyna.server.controller.course;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentFavorDTO;
import com.geeboo.dyna.server.service.course.IDynaCourseFavorAppService;
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
@Api(tags = "动态_课程点赞表相关接口}")
@RestController
@RequestMapping("/facade/dyna/course/favor/app")
public class DynaCourseFavorAppController {
    @Autowired
    private IDynaCourseFavorAppService dynaFavorAppService;

    /**
     * 点赞
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/doFavor")
    public ObjectResponse<Boolean> doFavor(@RequestBody DynaCourseCommentFavorDTO dto) {
        return dynaFavorAppService.doFavor(dto);
    }
}
