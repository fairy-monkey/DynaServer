package com.geeboo.dyna.server.client.facade.digest;


import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestFavorDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaDigestFavorAppFacade {
    /**
     * @description：书摘点赞
     * @author：luozh
     * @date：2018/9/28 15:52
     */
    @PostMapping(value = "/DynaServer/facade/dyna/digest/app/digestFavor")
    BaseResponse digestFavor(@RequestBody DynaDigestFavorDTO dto);
}