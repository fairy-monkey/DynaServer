package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaTopicController {
    @Autowired
    private IDynaTopicService dynaTopicService;

    /**
     * 增加动态_话题表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/topic", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicDTO dto) {
        return dynaTopicService.add(dto);
    }

    /**
     * 修改动态_话题表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/topic", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicDTO dto) {
        return dynaTopicService.update(dto);
    }

    /**
     * 删除动态_话题表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/topic", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicDTO dto) {
        Integer dynaTopicId = dto.getDynaTopicId();
        return dynaTopicService.delete(dynaTopicId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/topic/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicDTO> page(@RequestBody PageRestRequest<DynaTopicDTO> pageRestRequest) {
        return dynaTopicService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_话题表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/topic/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicDTO> findByCondition(@RequestBody DynaTopicDTO dto) {
        return dynaTopicService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_话题表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/topic/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicDTO> findById(@RequestBody DynaTopicDTO dto) {
        Integer dynaTopicId = dto.getDynaTopicId();
        return dynaTopicService.findById(dynaTopicId);
    }

    @PutMapping(value = "/topic/publish")
    public BaseResponse publish(@RequestBody DynaTopicDTO dto) {
        return dynaTopicService.publish(dto.getDynaTopicId());
    }

    @PutMapping(value = "/topic/off")
    public BaseResponse off(@RequestBody DynaTopicDTO dto) {
        return dynaTopicService.off(dto.getDynaTopicId());
    }

    @PutMapping(value = "/topic/recommend")
    public BaseResponse recommend(@RequestBody DynaTopicDTO dto) {
        return dynaTopicService.recommend(dto.getDynaTopicId(), dto.getIndexNo());
    }

    @PutMapping(value = "/topic/initAppTopicSort")
    public BaseResponse initAppTopicSort(@RequestBody DynaTopicDTO dto) {
        return dynaTopicService.initAppTopicSort(dto != null ? dto.getDynaTopicId() : null);
    }

    @PutMapping(value = "/topic/timeToPublish")
    public BaseResponse timeToPublish(@RequestBody DynaTopicDTO dto) {
        return dynaTopicService.timeToPublish(dto);
    }

    @GetMapping(value = "/topic/timeToPublishTask")
    public BaseResponse timeToPublishTask() {
        return dynaTopicService.timeToPublishTask();
    }

    /**
     * 话题缓存入库
     *
     * @return
     */
    @PostMapping(value = "/topic/flushRedisToDb")
    public BaseResponse flushRedisToDb() {
        return dynaTopicService.flushRedisToDb();
    }
}
