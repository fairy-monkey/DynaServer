package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaAttentionSquireDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorDTO;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/29 14:45
 */
public interface IDynaSquireReadService {
    /**
     * <p>分页查询</p>
     * <ol>
     * <li>根据请求的ID，查询缓存中zset下一页的ID集合</li>
     * <li>到缓存中根据ID批量查找对象值</li>
     * <li>缓存中不存在或者数量不足，则到数据库继续查找整个对象（默认认为ID不在缓存中，对象也不在）</li>
     * <li>到缓存中批量查找点赞数，如果存在，则覆盖现有列表中的数量</li>
     * <li>切记，点赞数缓存入库，也要更新redis中的对象</li>
     * </ol>
     *
     * @param dto  查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaSquireDTO> page(DynaSquireDTO dto, Page<DynaSquireDTO> page);
    /**
     * 关注列表分页查询，直连数据库，后面再优化
     *
     * @param dto  查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaSquireDTO> attentionPage(DynaAttentionSquireDTO dto, Page<DynaAttentionSquireDTO> page);

    /**
     * 用户列表分页查询，直连数据库，后面再优化
     *
     * @param dto  查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaSquireDTO> userPage(DynaSquireDTO dto, Page<DynaSquireDTO> page);

    /**
     * 通过条件获取动态_广场信息表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaSquireDTO dto);

    /**
     * 通过ID获取动态_广场信息表实体
     *
     * @param id 主键ID
     * @return
     */
    ObjectResponse<DynaSquireDTO> findById(Integer id);

    /**
     * 根据ID查询详情
     *
     * @param id
     * @return
     */
    ObjectResponse<DynaSquireDTO> findDetailById(Integer id);

    ObjectResponse<DynaSquireDTO> findSquireDetail(DynaSquireFavorDTO dto);

    /**
     * 获取某个用户的动态总数
     *
     * @param userId
     * @return
     */
    ObjectResponse<Long> getUserSquireNum(Integer userId);


    /**
     * 通过条件获取动态_广场信息列表
     *
     * @param dto 查询条件
     * @return
     */
    TableResultResponse<DynaSquireDTO> findByConditionList(DynaSquireDTO dto);

}
