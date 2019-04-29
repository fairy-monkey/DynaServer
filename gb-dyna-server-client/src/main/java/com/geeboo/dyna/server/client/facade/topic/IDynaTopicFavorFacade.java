package com.geeboo.dyna.server.client.facade.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicFavorDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaTopicFavorFacade {
    /**
     * 增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicFavor", method = RequestMethod.POST)
    BaseResponse add(@RequestBody DynaTopicFavorDTO dto);

    /**
     * 修改
     *
     * @param dto 待需改的实体对象
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicFavor", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaTopicFavorDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicFavor", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaTopicFavorDTO dto);

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicFavor/page", method = RequestMethod.POST)
    TableResultResponse<DynaTopicFavorDTO> page(@RequestBody PageRestRequest<DynaTopicFavorDTO> pageRestRequest);

    /**
     * 通过ID获取动态_话题点赞表实体
     *
     * @param dto 主键ID
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicFavor/findById", method = RequestMethod.POST)
    ObjectResponse<DynaTopicFavorDTO> findById(@RequestBody DynaTopicFavorDTO dto);

    /**
     * 通过条件获取动态_话题点赞表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/topicFavor/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaTopicFavorDTO> findByCondition(DynaTopicFavorDTO dto);
}