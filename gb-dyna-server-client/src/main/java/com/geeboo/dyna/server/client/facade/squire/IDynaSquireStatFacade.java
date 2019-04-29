package com.geeboo.dyna.server.client.facade.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireStatDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireStatFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squireStat", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaSquireStatDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squireStat", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaSquireStatDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squireStat", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaSquireStatDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squireStat/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireStatDTO> page(@RequestBody PageRestRequest<DynaSquireStatDTO> pageRestRequest);

    /**
     * 通过ID获取动态_广场动态统计表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squireStat/findById", method = RequestMethod.POST)
    ObjectResponse<DynaSquireStatDTO> findById(@RequestBody DynaSquireStatDTO dto);

    /**
     * 通过条件获取动态_广场动态统计表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squireStat/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaSquireStatDTO> findByCondition(DynaSquireStatDTO dto);
}

