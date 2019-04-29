package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.squire.*;
import com.geeboo.dyna.server.constant.OperateEnum;
import com.geeboo.dyna.server.entity.squire.DynaSquireDO;

import java.util.List;
import java.util.Set;

public interface IDynaSquireWriteService {

    /**
     * 修改动态_广场信息表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaSquireDTO dto);

    /**
     * 删除动态_广场信息表
     *
     * @param dto 系统主键&创建人
     * @return
     */
    BaseResponse delete(DynaSquireDTO dto);

    /**
     * 通过笔记增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    ObjectResponse<DynaSquireNoteDTO> addSquireFromNote(DynaSquireNoteDTO dto);

    /**
     * 通过书增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    ObjectResponse<DynaSquireBookDTO> addSquireFromBook(DynaSquireBookDTO dto);

    /**
     * 通过读后感增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    ObjectResponse<DynaSquireReadBookDTO> addSquireFromReadBook(DynaSquireReadBookDTO dto);

    /**
     * 通过分享书摘增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    ObjectResponse<DynaSquireBookDTO> addSquireFromShareBook(DynaSquireBookDTO dto);

    /**
     * 通过分享档案增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    ObjectResponse<DynaSquireBookDTO> addSquireFromRecord(DynaSquireRecordDTO dto);

    /**
     * 自增个人动态数量
     * 异常不会抛出，这里不需要回滚
     *
     * @param userId
     * @param operate
     * @return
     */
    BaseResponse incrementSquireNum(Integer userId, OperateEnum operate);

    /**
     * 批量初始化用户动态统计
     * 这个最好导入数据的时候初始化，不然很慢
     *
     * @param userIdSet
     * @return
     */
    BaseResponse initializeSquireNum(Set<Integer> userIdSet);

    /**
     * 自增动态点赞数量
     * 异常不会抛出，这里不需要回滚
     *
     * @param dynaSquireId
     * @param operate
     * @return
     */
    BaseResponse incrementFavorNum(Integer dynaSquireId, OperateEnum operate);

    /**
     * 批量初始化动态点赞统计
     *
     * @param dynaSquireIdSet
     * @return
     */
    BaseResponse initializeFavorNum(Set<Integer> dynaSquireIdSet);

    /**
     * 自增动态评论数量
     * 异常不会抛出，这里不需要回滚
     *
     * @param dynaSquireId
     * @param operate
     * @return
     */
    BaseResponse incrementCommentNum(Integer dynaSquireId, OperateEnum operate);

    /**
     * 批量初始化动态评论统计
     *
     * @param dynaSquireIdSet
     * @return
     */
    BaseResponse initializeCommentNum(Set<Integer> dynaSquireIdSet);

    /**
     * 批量更新评论数
     *
     * @param list
     * @return
     */
    void batchUpdateCommentNum(List<DynaSquireDO> list);

    /**
     * 批量更新点赞数
     *
     * @param list
     * @return
     */
    void batchUpdateFavorNum(List<DynaSquireDO> list);
}


