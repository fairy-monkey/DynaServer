package com.geeboo.dyna.server.client.facade.course;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentListDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseStatDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/14 11:01
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaCourseCommentAppFacade {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/course/comment/app/getCommentPage")
    TableResultResponse<DynaCourseCommentListDTO> getCommentPage(
                @RequestBody PageRestRequest<DynaCourseCommentListDTO> page);

    /**
     * 获取评论详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/DynaServer/facade/dyna/course/comment/app/{id}")
    DynaCourseCommentListDTO getCommentDetail(@PathVariable(value = "id") Integer id);

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/course/comment/app/comment")
    ObjectResponse<DynaCourseCommentDTO> addComment(@RequestBody DynaCourseCommentDTO dto);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/DynaServer/facade/dyna/course/comment/app/comment")
    BaseResponse deleteComment(@RequestBody DynaCourseCommentDTO dto);

    /**
     * 获取评论统计
     */
    @GetMapping("/DynaServer/facade/dyna/course/comment/app/courseStat/{courseId}")
    ObjectResponse<DynaCourseStatDTO> getCourseStat(@PathVariable(value = "courseId") Integer courseId);

    @RequestMapping(value = "/DynaServer/facade/dyna/course/comment/app/update", method = RequestMethod.POST)
    BaseResponse updateComment(@RequestBody DynaCourseCommentDTO dto);
}
