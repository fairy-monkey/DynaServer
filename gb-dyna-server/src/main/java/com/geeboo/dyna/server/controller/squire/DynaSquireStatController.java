package com.geeboo.dyna.server.controller.squire;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireStatDTO;
import com.geeboo.dyna.server.service.squire.IDynaSquireStatService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场动态统计表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaSquireStatController {
    @Autowired
    private IDynaSquireStatService dynaSquireStatService;

    /**
     * 增加动态_广场动态统计表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/squireStat", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaSquireStatDTO dto) {
        return dynaSquireStatService.add(dto);
    }

    /**
     * 修改动态_广场动态统计表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/squireStat", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaSquireStatDTO dto) {
        return dynaSquireStatService.update(dto);
    }

    /**
     * 删除动态_广场动态统计表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/squireStat", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaSquireStatDTO dto) {
        Integer dynaSquireStatId = dto.getDynaSquireStatId();
        return dynaSquireStatService.delete(dynaSquireStatId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/squireStat/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireStatDTO> page(@RequestBody PageRestRequest<DynaSquireStatDTO> pageRestRequest) {
        return dynaSquireStatService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_广场动态统计表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/squireStat/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaSquireStatDTO> findByCondition(@RequestBody DynaSquireStatDTO dto) {
        return dynaSquireStatService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_广场动态统计表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/squireStat/findById", method = RequestMethod.POST)
    ObjectResponse<DynaSquireStatDTO> findById(@RequestBody DynaSquireStatDTO dto) {
        Integer dynaSquireStatId = dto.getDynaSquireStatId();
        return dynaSquireStatService.findById(dynaSquireStatId);
    }
}
