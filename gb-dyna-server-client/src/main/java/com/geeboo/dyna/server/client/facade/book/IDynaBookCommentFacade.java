package com.geeboo.dyna.server.client.facade.book;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 辛建腾 guomy 创建时间:2018/9/14 11:01
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaBookCommentFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookComment/create", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentDTO> create(@RequestBody DynaBookCommentDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookComment/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody DynaBookCommentDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookComment/delete", method = RequestMethod.POST)
    BaseResponse delete(@RequestBody DynaBookCommentDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookComment/page", method = RequestMethod.POST)
    TableResultResponse<DynaBookCommentDTO> page(@RequestBody PageRestRequest<DynaBookCommentDTO> pageRestRequest);


    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookComment/query", method = RequestMethod.POST)
    TableResultResponse<DynaBookCommentDTO> query(@RequestBody DynaBookCommentDTO dto);

    /**
     * 通过ID获取动态_图书评论表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookComment/findById", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentDTO> findById(@RequestBody DynaBookCommentDTO dto);

    /**
     * 通过条件获取动态_图书评论表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookComment/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentDTO> findByCondition(DynaBookCommentDTO dto);

    /**
     * 通过ID隐藏动态_图书评论表实体
     *
     * @param dto 隐藏评论
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dynaBookComment/hiddenComment")
    BaseResponse hiddenComment(@RequestBody DynaBookCommentDTO dto);

    /**
     * 设置点赞数
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dynaBookComment/setFavorNum")
    BaseResponse setFavorNum(@RequestBody DynaBookCommentDTO dto);
}