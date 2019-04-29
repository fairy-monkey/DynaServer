package com.geeboo.dyna.server.client.facade.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicStatDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/5 17:35
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaTopicStatFacade {
    /**
     * 根据话题ID批量获取统计信息
     *
     * @param topicIdSet
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topicStat/batchGetStatByTopicId")
    ObjectResponse<Map<Integer, DynaTopicStatDTO>> batchGetStatByTopicId(@RequestBody Set<Integer> topicIdSet);

    /**
     * 初始化话题统计表
     *
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/topicStat/initDynaTopicStatService")
    BaseResponse initDynaTopicStatService();
}