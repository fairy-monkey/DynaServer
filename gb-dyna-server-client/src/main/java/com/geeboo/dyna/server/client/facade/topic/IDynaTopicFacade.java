package com.geeboo.dyna.server.client.facade.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaTopicFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topic", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topic", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topic", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topic/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicDTO> page(@RequestBody PageRestRequest<DynaTopicDTO> pageRestRequest);

    /**
     * 通过ID获取动态_话题表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topic/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicDTO> findById(@RequestBody DynaTopicDTO dto);

    /**
     * 通过条件获取动态_话题表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topic/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicDTO> findByCondition(@RequestBody DynaTopicDTO dto);

    /**
     * 上架
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/topic/publish")
    BaseResponse publish(@RequestBody DynaTopicDTO dto);

    /**
     * 下架
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/topic/off")
    BaseResponse off(@RequestBody DynaTopicDTO dto);

    /**
     * 推荐
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/topic/recommend")
    BaseResponse recommend(@RequestBody DynaTopicDTO dto);

    /**
     * 初始化app端排序
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/topic/appTopicSort")
    BaseResponse initAppTopicSort(@RequestBody DynaTopicDTO dto);

    /**
     * 定时发布
     *
     * @param dto
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/topic/timeToPublish")
    BaseResponse timeToPublish(@RequestBody DynaTopicDTO dto);

    /**
     * 定时发布任务
     *
     * @return
     */
    @GetMapping(value = "/DynaServer/facade/dyna/topic/timeToPublishTask")
    BaseResponse timeToPublishTask();

    /**
     * 话题缓存入库
     *
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topic/flushRedisToDb")
    BaseResponse flushRedisToDb();
}
