package com.geeboo.dyna.server.controller.book;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentListDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookStatDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireReadBookDTO;
import com.geeboo.dyna.server.service.book.IDynaBookCommentAppService;
import com.geeboo.dyna.server.service.book.IDynaBookCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/14 11:03
 */
@IgnoreClientToken
@Api(tags = "动态_评论APP相关接口}")
@RestController
@RequestMapping("/facade/dyna/book/comment/app")
public class DynaBookCommentAppController {
    @Autowired
    private IDynaBookCommentAppService dynaBookCommentAppService;
    @Autowired
    private IDynaBookCommentService dynaBookCommentService;

    @PostMapping(value = "/getCommentPage")
    public TableResultResponse<DynaBookCommentListDTO> getCommentPage(
            @RequestBody PageRestRequest<DynaBookCommentListDTO> page) {
        return dynaBookCommentAppService.getCommentPage(page);
    }

    /**
     * 获取评论详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public DynaBookCommentListDTO getCommentDetail(@PathVariable(value = "id") Integer id) {
        return dynaBookCommentAppService.getCommentDetail(id);
    }

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/comment")
    public ObjectResponse<DynaBookCommentDTO> addComment(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentAppService.addComment(dto);
    }

    /**
     * 添加读后评论，同时发布到广场
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/read/comment")
    public ObjectResponse<DynaSquireReadBookDTO> addReadComment(@RequestBody DynaSquireReadBookDTO dto) {
        return dynaBookCommentAppService.addReadComment(dto);
    }

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/comment")
    public BaseResponse deleteComment(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentAppService.deleteComment(dto);
    }

    /**
     * 获取评论统计
     */
    @GetMapping("/bookStat/{bookUserId}")
    ObjectResponse<DynaBookStatDTO> getBookStat(@PathVariable(value = "bookUserId") Integer bookUserId) {
        return dynaBookCommentAppService.getBookStat(bookUserId);
    }

    @PostMapping(value = "/getCommentList")
    public TableResultResponse<DynaBookCommentDTO> getCommentList(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentService.query(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse updateComment(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentService.updateComment(dto);
    }
}
