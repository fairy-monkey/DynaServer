package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicFavorDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicFavorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题点赞表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaTopicFavorController {
    @Autowired
    private IDynaTopicFavorService dynaTopicFavorService;

    /**
     * 增加动态_话题点赞表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/topicFavor", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicFavorDTO dto) {
        return dynaTopicFavorService.add(dto);
    }

    /**
     * 修改动态_话题点赞表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/topicFavor", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicFavorDTO dto) {
        return dynaTopicFavorService.update(dto);
    }

    /**
     * 删除动态_话题点赞表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/topicFavor", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicFavorDTO dto) {
        Integer dynaTopicFavorId = dto.getDynaTopicFavorId();
        return dynaTopicFavorService.delete(dynaTopicFavorId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/topicFavor/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicFavorDTO> page(@RequestBody PageRestRequest<DynaTopicFavorDTO> pageRestRequest) {
        return dynaTopicFavorService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_话题点赞表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/topicFavor/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicFavorDTO> findByCondition(@RequestBody DynaTopicFavorDTO dto) {
        return dynaTopicFavorService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_话题点赞表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/topicFavor/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicFavorDTO> findById(@RequestBody DynaTopicFavorDTO dto) {
        Integer dynaTopicFavorId = dto.getDynaTopicFavorId();
        return dynaTopicFavorService.findById(dynaTopicFavorId);
    }
}
