package com.geeboo.dyna.server.client.facade.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireCommentFacade {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squireComment/page")
    TableResultResponse<DynaSquireCommentListDTO> page(
                @RequestBody PageRestRequest<DynaSquireCommentListDTO> page);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/DynaServer/facade/dyna/squireComment/delete")
    BaseResponse deleteComment(@RequestBody DynaSquireCommentListDTO dto);




}
