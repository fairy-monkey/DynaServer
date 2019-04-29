package com.geeboo.dyna.server.controller.course;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.initdynastat.InitDynaCourseStatService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场动态统计表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaCourseStatController {

    @Autowired
    private InitDynaCourseStatService initDynaCourseStatService;


    /**
     * 初始化共读圈统计表
     */
    @PostMapping(value = "/courseStat/initDynaCourseStatService")
    public BaseResponse initDynaCourseStatService() {
        new Thread(initDynaCourseStatService).start();
        return BaseResponse.success("开始初始化！！");
    }

}
