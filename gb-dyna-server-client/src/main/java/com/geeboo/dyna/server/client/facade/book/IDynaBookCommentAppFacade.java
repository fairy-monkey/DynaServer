package com.geeboo.dyna.server.client.facade.book;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentListDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookStatDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireReadBookDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/14 11:01
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaBookCommentAppFacade {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/book/comment/app/getCommentPage")
    TableResultResponse<DynaBookCommentListDTO> getCommentPage(
                @RequestBody PageRestRequest<DynaBookCommentListDTO> page);

    /**
     * 获取评论详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/DynaServer/facade/dyna/book/comment/app/{id}")
    DynaBookCommentListDTO getCommentDetail(@PathVariable(value = "id") Integer id);

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/book/comment/app/comment")
    ObjectResponse<DynaBookCommentDTO> addComment(@RequestBody DynaBookCommentDTO dto);

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/book/comment/app/read/comment")
    ObjectResponse<DynaSquireReadBookDTO> addReadComment(@RequestBody DynaSquireReadBookDTO dto);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/DynaServer/facade/dyna/book/comment/app/comment")
    BaseResponse deleteComment(@RequestBody DynaBookCommentDTO dto);

    /**
     * 获取评论统计
     */
    @GetMapping("/DynaServer/facade/dyna/book/comment/app/bookStat/{bookUserId}")
    ObjectResponse<DynaBookStatDTO> getBookStat(@PathVariable(value = "bookUserId") Integer bookUserId);

    /**
     * 评论列表
     */
    @PostMapping(value = "/DynaServer/facade/dyna/book/comment/app/getCommentList")
    TableResultResponse<DynaBookCommentDTO> getCommentList(@RequestBody DynaBookCommentDTO dto);

    @RequestMapping(value = "/DynaServer/facade/dyna/book/comment/app/update", method = RequestMethod.POST)
    BaseResponse updateComment(@RequestBody DynaBookCommentDTO dto);
}
