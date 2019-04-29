package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicDTO;

public interface IDynaTopicService {
    /**
     * 增加动态_话题表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaTopicDTO dto);

    /**
     * 修改动态_话题表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaTopicDTO dto);

    /**
     * 删除动态_话题表
     * 同时删除内容和统计信息
     * 缓存入库时如果表记录不存在，将会自动跳过
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
    TableResultResponse<DynaTopicDTO> page(DynaTopicDTO dto, Page<DynaTopicDTO> page);

    /**
     * 通过ID获取动态_话题表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_话题表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaTopicDTO dto);

    /**
     * 发布
     *
     * @param id
     * @return
     */
    BaseResponse publish(Integer id);

    /**
     * 下架
     * 数量缓存自动失效
     *
     * @param id
     * @return
     */
    BaseResponse off(Integer id);

    /**
     * 推荐
     *
     * @param id
     * @param sort
     * @return
     */
    BaseResponse recommend(Integer id, Integer sort);

    /**
     * 定时发布
     * @param dto
     * @return
     */
    BaseResponse timeToPublish(DynaTopicDTO dto);

    /**
     * 初始化app端排序
     *
     * @param topicId
     * @return
     */
    BaseResponse initAppTopicSort(Integer topicId);

    /**
     * 定时发布任务
     *
     * @return
     */
    BaseResponse timeToPublishTask();

    /**
     * 从redis更新到数据库
     * @return
     */
    BaseResponse flushRedisToDb();
}
