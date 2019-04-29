package com.geeboo.dyna.server.controller.book;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookComplaintDTO;
import com.geeboo.dyna.server.service.book.IDynaBookComplaintService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_图书举报表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaBookComplaintController {
    @Autowired
    private IDynaBookComplaintService dynaBookComplaintService;

    /**
     * 增加动态_图书举报表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/bookComplaint", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaBookComplaintDTO dto) {
        return dynaBookComplaintService.add(dto);
    }

    /**
     * 修改动态_图书举报表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/bookComplaint", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaBookComplaintDTO dto) {
        return dynaBookComplaintService.update(dto);
    }

    /**
     * 删除动态_图书举报表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/bookComplaint", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaBookComplaintDTO dto) {
        Integer dynaBookComplaintId = dto.getDynaBookComplaintId();
        return dynaBookComplaintService.delete(dynaBookComplaintId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/bookComplaint/page", method = RequestMethod.POST)
    TableResultResponse<DynaBookComplaintDTO> page(@RequestBody PageRestRequest<DynaBookComplaintDTO> pageRestRequest) {
        return dynaBookComplaintService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_图书举报表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/bookComplaint/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaBookComplaintDTO> findByCondition(@RequestBody DynaBookComplaintDTO dto) {
        return dynaBookComplaintService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_图书举报表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/bookComplaint/findById", method = RequestMethod.POST)
    ObjectResponse<DynaBookComplaintDTO> findById(@RequestBody DynaBookComplaintDTO dto) {
        Integer dynaBookComplaintId = dto.getDynaBookComplaintId();
        return dynaBookComplaintService.findById(dynaBookComplaintId);
    }
}
