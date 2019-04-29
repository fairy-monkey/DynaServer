package com.geeboo.dyna.server.client.facade.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireCommentAppFacade {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/comment/app/page")
    TableResultResponse<DynaSquireCommentListDTO> getCommentPage(
            @RequestBody PageRestRequest<DynaSquireCommentListDTO> page);

    /**
     * 获取评论详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/DynaServer/facade/dyna/squire/comment/app/{id}")
    DynaSquireCommentListDTO getCommentDetail(@PathVariable(value = "id") Integer id);

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/comment/app")
    ObjectResponse<DynaSquireCommentDTO> addComment(@RequestBody DynaSquireCommentDTO dto);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/DynaServer/facade/dyna/squire/comment/app")
    BaseResponse deleteComment(@RequestBody DynaSquireCommentDTO dto);

    /**
     * 更新评论
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/squire/comment/app/update", method = RequestMethod.POST)
    BaseResponse updateComment(@RequestBody DynaSquireCommentDTO dto);

    @RequestMapping(value = "/DynaServer/facade/dyna/squire/comment/app/getById", method = RequestMethod.POST)
    ObjectResponse<DynaSquireCommentDTO> getById(@RequestBody DynaSquireCommentDTO dto);
}
