package com.geeboo.dyna.server.client.facade.course;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaCourseCommentFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaCourseComment/create", method = RequestMethod.POST)
    ObjectResponse<DynaCourseCommentDTO> create(@RequestBody DynaCourseCommentDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaCourseComment/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody DynaCourseCommentDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaCourseComment/delete", method = RequestMethod.POST)
    BaseResponse delete(@RequestBody DynaCourseCommentDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaCourseComment/page", method = RequestMethod.POST)
    TableResultResponse<DynaCourseCommentDTO> page(@RequestBody PageRestRequest<DynaCourseCommentDTO> pageRestRequest);


    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaCourseComment/query", method = RequestMethod.POST)
    TableResultResponse<DynaCourseCommentDTO> query(@RequestBody DynaCourseCommentDTO dto);

    /**
     * 通过ID获取动态_课程评论表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaCourseComment/findById", method = RequestMethod.POST)
    ObjectResponse<DynaCourseCommentDTO> findById(@RequestBody DynaCourseCommentDTO dto);

    /**
     * 通过条件获取动态_课程评论表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaCourseComment/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaCourseCommentDTO> findByCondition(DynaCourseCommentDTO dto);

    /**
     * 通过ID隐藏动态_课程评论表实体
     *
     * @param dto 隐藏评论
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dynaCourseComment/hiddenComment")
    BaseResponse hiddenComment(@RequestBody DynaCourseCommentDTO dto);

    /**
     * 设置点赞数
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dynaCourseComment/setFavorNum")
    BaseResponse setFavorNum(@RequestBody DynaCourseCommentDTO dto);
}