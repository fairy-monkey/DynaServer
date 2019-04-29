package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicStatDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicStatDO;

import java.util.Map;
import java.util.Set;

public interface IDynaTopicStatService {
    /**
     * 增加动态_话题动态统计表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaTopicStatDTO dto);

    /**
     * 修改动态_话题动态统计表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaTopicStatDTO dto);

    /**
     * 删除动态_话题动态统计表
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
    TableResultResponse<DynaTopicStatDTO> page(DynaTopicStatDTO dto, Page<DynaTopicStatDTO> page);

    /**
     * 条件查询所有
     *
     * @param dto
     * @return
     */
    TableResultResponse<DynaTopicStatDTO> query(DynaTopicStatDTO dto);

    /**
     * 通过ID获取动态_话题动态统计表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_话题动态统计表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaTopicStatDTO dto);

    /**
     * 根据话题ID查找
     *
     * @param topicId
     * @return
     */
    DynaTopicStatDTO findByTopicId(Integer topicId);

    /**
     * 根据话题ID批量获取统计信息
     *
     * @param topicIdSet
     * @return
     */
    ObjectResponse<Map<Integer, DynaTopicStatDTO>> batchGetStatByTopicId(Set<Integer> topicIdSet);

    /**
     * 更新不为空的字段
     *
     * @param dynaTopicStatDO
     * @return
     */
    int updateByPrimaryKeySelective(DynaTopicStatDO dynaTopicStatDO);
}