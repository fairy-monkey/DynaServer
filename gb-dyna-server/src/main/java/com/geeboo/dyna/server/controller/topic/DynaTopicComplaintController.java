package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicComplaintDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicComplaintService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题举报表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaTopicComplaintController {
    @Autowired
    private IDynaTopicComplaintService dynaTopicComplaintService;

    /**
     * 增加动态_话题举报表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/topicComplaint", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicComplaintDTO dto) {
        return dynaTopicComplaintService.add(dto);
    }

    /**
     * 修改动态_话题举报表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/topicComplaint", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicComplaintDTO dto) {
        return dynaTopicComplaintService.update(dto);
    }

    /**
     * 删除动态_话题举报表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/topicComplaint", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicComplaintDTO dto) {
        Integer dynaTopicComplaintId = dto.getDynaTopicComplaintId();
        return dynaTopicComplaintService.delete(dynaTopicComplaintId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/topicComplaint/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicComplaintDTO> page(@RequestBody PageRestRequest<DynaTopicComplaintDTO> pageRestRequest) {
        return dynaTopicComplaintService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_话题举报表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/topicComplaint/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicComplaintDTO> findByCondition(@RequestBody DynaTopicComplaintDTO dto) {
        return dynaTopicComplaintService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_话题举报表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/topicComplaint/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicComplaintDTO> findById(@RequestBody DynaTopicComplaintDTO dto) {
        Integer dynaTopicComplaintId = dto.getDynaTopicComplaintId();
        return dynaTopicComplaintService.findById(dynaTopicComplaintId);
    }
}
