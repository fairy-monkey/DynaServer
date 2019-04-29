package com.geeboo.dyna.server.controller.course;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentDTO;
import com.geeboo.dyna.server.service.course.IDynaCourseCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_课程评论表相关接口}")
@RestController
@RequestMapping("/facade/dynaCourseComment")
public class DynaCourseCommentController {
    @Autowired
    private IDynaCourseCommentService dynaCourseCommentService;

    /**
     * 增加动态_课程评论表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    BaseResponse create(@RequestBody DynaCourseCommentDTO dto) {
        return dynaCourseCommentService.add(dto);
    }

    /**
     * 修改动态_课程评论表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody DynaCourseCommentDTO dto) {
        return dynaCourseCommentService.update(dto);
    }

    /**
     * 删除动态_课程评论表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    BaseResponse delete(@RequestBody DynaCourseCommentDTO dto) {
        Integer dynaCourseCommentId = dto.getDynaCourseCommentId();
        return dynaCourseCommentService.delete(dynaCourseCommentId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    TableResultResponse<DynaCourseCommentDTO> page(@RequestBody PageRestRequest<DynaCourseCommentDTO> pageRestRequest) {
        return dynaCourseCommentService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    TableResultResponse<DynaCourseCommentDTO> query(@RequestBody DynaCourseCommentDTO dto) {
        return dynaCourseCommentService.query(dto);
    }

    /**
     * 通过ID获取动态_课程评论表实体
     *
     * @param dto 包含系统主键ID
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    ObjectResponse<DynaCourseCommentDTO> findById(@RequestBody DynaCourseCommentDTO dto) {
        Integer dynaCourseCommentId = dto.getDynaCourseCommentId();
        return dynaCourseCommentService.findById(dynaCourseCommentId);
    }

    /**
     * 通过条件获取动态_课程评论表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaCourseCommentDTO> findByCondition(@RequestBody DynaCourseCommentDTO dto) {
        return dynaCourseCommentService.findByCondition(dto);
    }

    @PutMapping(value = "/hiddenComment")
    public BaseResponse hiddenComment(@RequestBody DynaCourseCommentDTO dto) {
        try {
            return dynaCourseCommentService.hiddenComment(dto);
        } catch (Throwable t) {
            return BaseResponse.failure(StatusEnum.FAILURE.getDescribe());
        }
    }

    @PutMapping(value = "/setFavorNum")
    public BaseResponse setFavorNum(@RequestBody DynaCourseCommentDTO dto) {
        return dynaCourseCommentService.setFavorNum(dto);
    }
}