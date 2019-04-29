package com.geeboo.dyna.server.client.facade.digest;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaDigestFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/add", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaDigestDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody DynaDigestDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/remove", method = RequestMethod.POST)
    BaseResponse remove(@RequestBody DynaDigestDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/page", method = RequestMethod.POST)
    TableResultResponse<DynaDigestDTO> page(@RequestBody PageRestRequest<DynaDigestDTO> pageRestRequest);

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/query", method = RequestMethod.POST)
    TableResultResponse<DynaDigestDTO> query(@RequestBody DynaDigestDTO dto);

    /**
     * 通过ID获取动态_书摘表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/findById", method = RequestMethod.POST)
    ObjectResponse<DynaDigestDTO> findById(@RequestBody DynaDigestDTO dto);

    /**
     * 通过条件获取动态_书摘表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaDigestDTO> findByCondition(DynaDigestDTO dto);

    /**
     * 发布
     *
     * @param dto 待发布的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/publish", method = RequestMethod.POST)
    BaseResponse publish(@RequestBody DynaDigestDTO dto);

    /**
     * 取消发布
     *
     * @param dto 待取消发布的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/off", method = RequestMethod.POST)
    BaseResponse off(@RequestBody DynaDigestDTO dto);

    /**
     * 定时发布
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaDigest/timeToPublish", method = RequestMethod.POST)
    BaseResponse timeToPublish(@RequestBody DynaDigestDTO dto);
}