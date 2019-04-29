package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicContentDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDynaTopicContentService {
    /**
     * 增加动态_话题内容表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaTopicContentDTO dto);

    /**
     * 修改动态_话题内容表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaTopicContentDTO dto);

    /**
     * 删除动态_话题内容表
     *
     * @param id 系统主键
     * @return
     */
    BaseResponse delete(Integer id);

    /**
     * 分页查询
     *
     * @param dto  查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaTopicContentDTO> page(DynaTopicContentDTO dto, Page<DynaTopicContentDTO> page);

    /**
     * 通过ID获取动态_话题内容表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_话题内容表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaTopicContentDTO dto);

    /**
     * 根据话题ID查找
     *
     * @param topicId
     * @return
     */
    DynaTopicContentDTO findByTopicId(Integer topicId);

    /**
     * 批量通过话题ID获取
     *
     * @param topicIdSet
     * @return
     */
    List<DynaTopicContentDTO> batchGetContentByTopicId(Set<Integer> topicIdSet);

    /**
     * 批量通过话题ID获取
     *
     * @param topicIdMap key代表查询的ID
     * @return
     */
    List<DynaTopicContentDTO> batchGetContentByTopicId(Map<Integer, ?> topicIdMap);
}