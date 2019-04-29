package com.geeboo.dyna.server.client.facade.digest;


import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestListDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaDigestAppFacade {
    /**
     * @description：书摘列表
     * @author：luozh
     * @date：2018/9/27 15:47
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/digest/app/getDigestPage", method = RequestMethod.POST)
    TableResultResponse<DynaDigestListDTO> getDigestPage(PageRestRequest<DynaDigestListDTO> pageRequest);
}