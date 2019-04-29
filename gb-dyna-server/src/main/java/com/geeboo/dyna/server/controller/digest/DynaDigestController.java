package com.geeboo.dyna.server.controller.digest;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestDTO;
import com.geeboo.dyna.server.service.digest.IDynaDigestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_书摘表相关接口}")
@RestController
@RequestMapping("/facade/dynaDigest")
public class DynaDigestController {
    @Autowired
    private IDynaDigestService dynaDigestService;

    /**
     * 增加动态_书摘表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaDigestDTO dto) {
        return dynaDigestService.add(dto);
    }

    /**
     * 修改动态_书摘表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody DynaDigestDTO dto) {
        return dynaDigestService.update(dto);
    }

    /**
     * 删除动态_书摘表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    BaseResponse remove(@RequestBody DynaDigestDTO dto) {
        Integer dynaDigestId = dto.getDynaDigestId();
        return dynaDigestService.remove(dynaDigestId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    TableResultResponse<DynaDigestDTO> page(@RequestBody PageRestRequest<DynaDigestDTO> pageRestRequest) {
        return dynaDigestService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    TableResultResponse<DynaDigestDTO> query(@RequestBody DynaDigestDTO dto) {
        return dynaDigestService.query(dto);
    }

    /**
     * 通过ID获取动态_书摘表实体
     *
     * @param dto 包含系统主键ID
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    ObjectResponse<DynaDigestDTO> findById(@RequestBody DynaDigestDTO dto) {
        Integer dynaDigestId = dto.getDynaDigestId();
        return dynaDigestService.findById(dynaDigestId);
    }

    /**
     * 通过条件获取动态_书摘表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaDigestDTO> findByCondition(@RequestBody DynaDigestDTO dto) {
        return dynaDigestService.findByCondition(dto);
    }

    /**
     * 发布
     *
     * @param dto 需要发布的实体
     * @return
     */
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    BaseResponse publish(@RequestBody DynaDigestDTO dto) {
        return dynaDigestService.publish(dto);
    }

    /**
     * 取消发布
     *
     * @param dto 需要取消发布的实体
     * @return
     */
    @RequestMapping(value = "/off", method = RequestMethod.POST)
    BaseResponse off(@RequestBody DynaDigestDTO dto) {
        return dynaDigestService.off(dto);
    }

    @RequestMapping(value = "/timeToPublish", method = RequestMethod.POST)
    public BaseResponse timeToPublish(@RequestBody DynaDigestDTO dto) {
        return dynaDigestService.timeToPublish(dto);
    }
}
