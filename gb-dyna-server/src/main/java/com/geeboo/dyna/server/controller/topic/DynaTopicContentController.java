package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicContentDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicContentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题内容表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaTopicContentController {
    @Autowired
    private IDynaTopicContentService dynaTopicContentService;

    /**
     * 增加动态_话题内容表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/topicContent", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicContentDTO dto) {
        return dynaTopicContentService.add(dto);
    }

    /**
     * 修改动态_话题内容表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/topicContent", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicContentDTO dto) {
        return dynaTopicContentService.update(dto);
    }

    /**
     * 删除动态_话题内容表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/topicContent", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicContentDTO dto) {
        Integer dynaTopicContentId = dto.getDynaTopicContentId();
        return dynaTopicContentService.delete(dynaTopicContentId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/topicContent/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicContentDTO> page(@RequestBody PageRestRequest<DynaTopicContentDTO> pageRestRequest) {
        return dynaTopicContentService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_话题内容表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/topicContent/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicContentDTO> findByCondition(@RequestBody DynaTopicContentDTO dto) {
        return dynaTopicContentService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_话题内容表实体
     *
     * @param id 主键ID
     * @return
     */
    @RequestMapping(value = "/topicContent/{id}", method = RequestMethod.GET)
    ObjectResponse<DynaTopicContentDTO> findById(@PathVariable(value = "id") Integer id) {
        return dynaTopicContentService.findById(id);
    }
}
