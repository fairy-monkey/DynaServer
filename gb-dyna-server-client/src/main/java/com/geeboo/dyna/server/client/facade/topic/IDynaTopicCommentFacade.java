package com.geeboo.dyna.server.client.facade.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaTopicCommentFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicComment", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicCommentDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicComment", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicCommentDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicComment", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicCommentDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicComment/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicCommentDTO> page(@RequestBody PageRestRequest<DynaTopicCommentDTO> pageRestRequest);

    /**
     * 通过ID获取动态_话题评论表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicComment/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicCommentDTO> findById(@RequestBody DynaTopicCommentDTO dto);

    /**
     * 通过条件获取动态_话题评论表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicComment/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicCommentDTO> findByCondition(DynaTopicCommentDTO dto);

    /**
     * 隐藏评论
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/topicComment/hiddenComment")
    BaseResponse hiddenComment(@RequestBody DynaTopicCommentDTO dto);

    /**
     * 设置点赞数
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/topicComment/setFavorNum")
    BaseResponse setFavorNum(@RequestBody DynaTopicCommentDTO dto);

    /**
     * 话题缓存入库
     *
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topicComment/flushRedisToDb")
    BaseResponse flushRedisToDb();
}