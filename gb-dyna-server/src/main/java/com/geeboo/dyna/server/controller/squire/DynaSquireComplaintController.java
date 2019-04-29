package com.geeboo.dyna.server.controller.squire;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireComplaintDTO;
import com.geeboo.dyna.server.service.squire.IDynaSquireComplaintService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场举报表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaSquireComplaintController {
    @Autowired
    private IDynaSquireComplaintService dynaSquireComplaintService;

    /**
     * 增加动态_广场举报表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/squireComplaint", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaSquireComplaintDTO dto) {
        return dynaSquireComplaintService.add(dto);
    }

    /**
     * 修改动态_广场举报表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/squireComplaint", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaSquireComplaintDTO dto) {
        return dynaSquireComplaintService.update(dto);
    }

    /**
     * 删除动态_广场举报表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/squireComplaint", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaSquireComplaintDTO dto) {
        Integer dynasquireComplaintId = dto.getSquireComplaintId();
        return dynaSquireComplaintService.delete(dynasquireComplaintId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/squireComplaint/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireComplaintDTO> page(@RequestBody PageRestRequest<DynaSquireComplaintDTO> pageRestRequest) {
        return dynaSquireComplaintService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_广场举报表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/squireComplaint/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaSquireComplaintDTO> findByCondition(@RequestBody DynaSquireComplaintDTO dto) {
        return dynaSquireComplaintService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_广场举报表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/squireComplaint/findById", method = RequestMethod.POST)
    ObjectResponse<DynaSquireComplaintDTO> findById(@RequestBody DynaSquireComplaintDTO dto) {
        Integer squireComplaintId = dto.getSquireComplaintId();
        return dynaSquireComplaintService.findById(squireComplaintId);
    }
}
