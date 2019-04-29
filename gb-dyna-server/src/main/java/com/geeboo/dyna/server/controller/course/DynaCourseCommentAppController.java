package com.geeboo.dyna.server.controller.course;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentListDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseStatDTO;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentAppService;
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
@RequestMapping("/facade/dyna/course/comment/app")
public class DynaCourseCommentAppController {
    @Autowired
    private IDynaCourseCommentAppService dynaCourseCommentAppService;

    @PostMapping(value = "/getCommentPage")
    public TableResultResponse<DynaCourseCommentListDTO> getCommentPage(
                @RequestBody PageRestRequest<DynaCourseCommentListDTO> page) {
        return dynaCourseCommentAppService.getCommentPage(page);
    }

    /**
     * 获取评论详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public DynaCourseCommentListDTO getCommentDetail(@PathVariable(value = "id") Integer id) {
        return dynaCourseCommentAppService.getCommentDetail(id);
    }

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/comment")
    public ObjectResponse<DynaCourseCommentDTO> addComment(@RequestBody DynaCourseCommentDTO dto) {
        return dynaCourseCommentAppService.addComment(dto);
    }

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/comment")
    public BaseResponse deleteComment(@RequestBody DynaCourseCommentDTO dto) {
        return dynaCourseCommentAppService.deleteComment(dto);
    }

    /**
     * 获取评论统计
     */
    @GetMapping("/courseStat/{courseId}")
    ObjectResponse<DynaCourseStatDTO> getCourseStat(@PathVariable(value = "courseId") Integer courseId) {
        return dynaCourseCommentAppService.getCourseStat(courseId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse updateComment(@RequestBody DynaCourseCommentDTO dto) {
        return dynaCourseCommentAppService.updateComment(dto);
    }
}
