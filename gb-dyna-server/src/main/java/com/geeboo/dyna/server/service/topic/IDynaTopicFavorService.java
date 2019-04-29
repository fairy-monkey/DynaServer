package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicFavorDTO;

public interface IDynaTopicFavorService {
    /**
     * 增加动态_话题点赞表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaTopicFavorDTO dto);

    /**
     * 修改动态_话题点赞表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaTopicFavorDTO dto);

    /**
     * 删除动态_话题点赞表
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
    TableResultResponse<DynaTopicFavorDTO> page(DynaTopicFavorDTO dto, Page<DynaTopicFavorDTO> page);

    /**
     * 通过ID获取动态_话题点赞表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_话题点赞表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaTopicFavorDTO dto);
}