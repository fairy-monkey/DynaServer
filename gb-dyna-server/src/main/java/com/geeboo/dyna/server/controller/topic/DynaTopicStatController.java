package com.geeboo.dyna.server.controller.topic;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.initdynastat.InitDynaTopicStatService;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicStatDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicStatService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_话题动态统计表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaTopicStatController {
    @Autowired
    private IDynaTopicStatService dynaTopicStatService;
    @Autowired
    private InitDynaTopicStatService initDynaTopicStatService;

    /**
     * 增加动态_话题动态统计表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/topicStat", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicStatDTO dto) {
        return dynaTopicStatService.add(dto);
    }

    /**
     * 修改动态_话题动态统计表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/topicStat", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicStatDTO dto) {
        return dynaTopicStatService.update(dto);
    }

    /**
     * 删除动态_话题动态统计表
     *
     * @param dto 包含系统主键
     * @return
     */
    @RequestMapping(value = "/topicStat", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicStatDTO dto) {
        Integer dynaTopicStatId = dto.getDynaTopicStatId();
        return dynaTopicStatService.delete(dynaTopicStatId);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/topicStat/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicStatDTO> page(@RequestBody PageRestRequest<DynaTopicStatDTO> pageRestRequest) {
        return dynaTopicStatService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_话题动态统计表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/topicStat/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicStatDTO> findByCondition(@RequestBody DynaTopicStatDTO dto) {
        return dynaTopicStatService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_话题动态统计表实体
     *
     * @param id 主键ID
     * @return
     */
    @RequestMapping(value = "/topicStat/{id}", method = RequestMethod.GET)
    ObjectResponse<DynaTopicStatDTO> findById(@PathVariable(value = "id") Integer id) {
        return dynaTopicStatService.findById(id);
    }

    @PostMapping(value = "/topicStat/batchGetStatByTopicId")
    public ObjectResponse<Map<Integer, DynaTopicStatDTO>> batchGetStatByTopicId(@RequestBody Set<Integer> topicIdSet) {
        return dynaTopicStatService.batchGetStatByTopicId(topicIdSet);
    }

    /**
     * 初始化话题统计表
     * @return
     */
    @PostMapping(value = "/topicStat/initDynaTopicStatService")
    public BaseResponse initDynaTopicStatService() {
        new Thread(initDynaTopicStatService).start();
        return BaseResponse.success("开始初始化！！");
    }

}
