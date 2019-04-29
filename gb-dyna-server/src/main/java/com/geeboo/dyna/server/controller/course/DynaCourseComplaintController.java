package com.geeboo.dyna.server.controller.course;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseComplaintDTO;
import com.geeboo.dyna.server.service.course.IDynaCourseComplaintService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_课程举报表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaCourseComplaintController {
    @Autowired
    private IDynaCourseComplaintService dynaCourseComplaintService;

    /**
     * 增加动态_课程举报表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/courseComplaint", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaCourseComplaintDTO dto) {
        return dynaCourseComplaintService.add(dto);
    }

    /**
     * 修改动态_课程举报表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/courseComplaint", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaCourseComplaintDTO dto) {
        return dynaCourseComplaintService.update(dto);
    }

    /**
     * 删除动态_课程举报表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/courseComplaint", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaCourseComplaintDTO dto) {
        Integer dynaTopicComplaintId = dto.getDynaCourseComplaintId();
        return dynaCourseComplaintService.delete(dynaTopicComplaintId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/courseComplaint/page", method = RequestMethod.POST)
    TableResultResponse<DynaCourseComplaintDTO> page(@RequestBody PageRestRequest<DynaCourseComplaintDTO> pageRestRequest) {
        return dynaCourseComplaintService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_话题举报表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/courseComplaint/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaCourseComplaintDTO> findByCondition(@RequestBody DynaCourseComplaintDTO dto) {
        return dynaCourseComplaintService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_课程举报表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/courseComplaint/findById", method = RequestMethod.POST)
    ObjectResponse<DynaCourseComplaintDTO> findById(@RequestBody DynaCourseComplaintDTO dto) {
        Integer dynaCourseComplaintId = dto.getDynaCourseComplaintId();
        return dynaCourseComplaintService.findById(dynaCourseComplaintId);
    }
}
