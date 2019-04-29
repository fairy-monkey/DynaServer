package com.geeboo.dyna.server.client.facade.squire;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireComplaintDTO;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 林诗炀 linsy 创建时间:2018/11/19 10:09
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireComplaintAppFacade {
    /**
     * 举报评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/complaint/app/complaint")
    BaseResponse complaint(@RequestBody DynaSquireComplaintDTO dto);
}
