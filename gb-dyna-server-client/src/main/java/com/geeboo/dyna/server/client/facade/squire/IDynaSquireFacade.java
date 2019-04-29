package com.geeboo.dyna.server.client.facade.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireFacade {


    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squire/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireDTO> page(@RequestBody PageRestRequest<DynaSquireDTO> pageRestRequest);



    /**
     * 删除
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squire/delete", method = RequestMethod.POST)
    BaseResponse delete(@RequestBody DynaSquireDTO dto);





}