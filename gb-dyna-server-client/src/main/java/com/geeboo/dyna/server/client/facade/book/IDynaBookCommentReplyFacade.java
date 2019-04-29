package com.geeboo.dyna.server.client.facade.book;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentReplyDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaBookCommentReplyFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookCommentReply/create", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentReplyDTO> create(@RequestBody DynaBookCommentReplyDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookCommentReply/update", method = RequestMethod.POST)
    BaseResponse update(@RequestBody DynaBookCommentReplyDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookCommentReply/delete", method = RequestMethod.POST)
    BaseResponse delete(@RequestBody DynaBookCommentReplyDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookCommentReply/page", method = RequestMethod.POST)
    TableResultResponse<DynaBookCommentReplyDTO> page(@RequestBody PageRestRequest<DynaBookCommentReplyDTO> pageRestRequest);


    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookCommentReply/query", method = RequestMethod.POST)
    TableResultResponse<DynaBookCommentReplyDTO> query(@RequestBody DynaBookCommentReplyDTO dto);

    /**
     * 通过ID获取动态_课程评论回复表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookCommentReply/findById", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentReplyDTO> findById(@RequestBody DynaBookCommentReplyDTO dto);

    /**
     * 通过条件获取动态_课程评论回复表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dynaBookCommentReply/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaBookCommentReplyDTO> findByCondition(DynaBookCommentReplyDTO dto);
}