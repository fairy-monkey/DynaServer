package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题评论表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaTopicCommentController {
    @Autowired
    private IDynaTopicCommentService dynaTopicCommentService;

    /**
     * 增加动态_话题评论表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/topicComment", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicCommentDTO dto) {
        return dynaTopicCommentService.add(dto);
    }

    /**
     * 修改动态_话题评论表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/topicComment", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicCommentDTO dto) {
        return dynaTopicCommentService.update(dto);
    }

    /**
     * 删除动态_话题评论表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/topicComment", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicCommentDTO dto) {
        Integer dynaTopicCommentId = dto.getDynaTopicCommentId();
        return dynaTopicCommentService.delete(dynaTopicCommentId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/topicComment/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicCommentDTO> page(@RequestBody PageRestRequest<DynaTopicCommentDTO> pageRestRequest) {
        return dynaTopicCommentService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_话题评论表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/topicComment/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicCommentDTO> findByCondition(@RequestBody DynaTopicCommentDTO dto) {
        return dynaTopicCommentService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_话题评论表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/topicComment/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicCommentDTO> findById(@RequestBody DynaTopicCommentDTO dto) {
        Integer dynaTopicCommentId = dto.getDynaTopicCommentId();
        return dynaTopicCommentService.findById(dynaTopicCommentId);
    }

    @PutMapping(value = "/topicComment/hiddenComment")
    public BaseResponse hiddenComment(@RequestBody DynaTopicCommentDTO dto) {
        return dynaTopicCommentService.hiddenComment(dto);
    }

    @PutMapping(value = "/topicComment/setFavorNum")
    public BaseResponse setFavorNum(@RequestBody DynaTopicCommentDTO dto) {
        return dynaTopicCommentService.setFavorNum(dto);
    }

    /**
     * 评论缓存入库
     *
     * @return
     */
    @PostMapping(value = "/topicComment/flushRedisToDb")
    public BaseResponse flushRedisToDb() {
        return dynaTopicCommentService.flushRedisToDb();
    }
}
