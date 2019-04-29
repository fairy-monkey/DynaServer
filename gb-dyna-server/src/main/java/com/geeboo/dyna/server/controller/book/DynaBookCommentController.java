package com.geeboo.dyna.server.controller.book;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentDTO;
import com.geeboo.dyna.server.service.book.IDynaBookCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_图书评论表相关接口}")
@RestController
@RequestMapping("/facade/dynaBookComment")
public class DynaBookCommentController {
    @Autowired
    private IDynaBookCommentService dynaBookCommentService;

    /**
     * 增加动态_图书评论表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    BaseResponse create(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentService.add(dto);
    }

    /**
     * 修改动态_图书评论表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentService.update(dto);
    }

    /**
     * 删除动态_图书评论表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    BaseResponse delete(@RequestBody DynaBookCommentDTO dto) {
        Integer dynaBookCommentId = dto.getDynaBookCommentId();
        return dynaBookCommentService.delete(dynaBookCommentId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    TableResultResponse<DynaBookCommentDTO> page(@RequestBody PageRestRequest<DynaBookCommentDTO> pageRestRequest) {
        return dynaBookCommentService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    TableResultResponse<DynaBookCommentDTO> query(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentService.query(dto);
    }

    /**
     * 通过ID获取动态_图书评论表实体
     *
     * @param dto 包含系统主键ID
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentDTO> findById(@RequestBody DynaBookCommentDTO dto) {
        Integer dynaBookCommentId = dto.getDynaBookCommentId();
        return dynaBookCommentService.findById(dynaBookCommentId);
    }

    /**
     * 通过条件获取动态_图书评论表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentDTO> findByCondition(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentService.findByCondition(dto);
    }

    @PutMapping(value = "/hiddenComment")
    public BaseResponse hiddenComment(@RequestBody DynaBookCommentDTO dto) {
        try {
            return dynaBookCommentService.hiddenComment(dto);
        } catch (Throwable t) {
            return BaseResponse.failure(StatusEnum.FAILURE.getDescribe());
        }
    }

    @PutMapping(value = "/setFavorNum")
    public BaseResponse setFavorNum(@RequestBody DynaBookCommentDTO dto) {
        return dynaBookCommentService.setFavorNum(dto);
    }
}