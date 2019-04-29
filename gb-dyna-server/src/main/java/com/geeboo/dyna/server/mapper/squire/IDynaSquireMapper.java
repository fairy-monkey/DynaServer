package com.geeboo.dyna.server.mapper.squire;

import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;
import com.geeboo.dyna.server.entity.squire.DynaSquireDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface IDynaSquireMapper extends Mapper<DynaSquireDO> {
    DynaSquireDTO findById(Integer dynaSquireId);

    int add(DynaSquireDTO dto);

    int update(DynaSquireDTO dto);

    int updateCommentNum(DynaSquireDTO dto);

    int updateFavorNum(DynaSquireDTO dto);

    int deleteDynaSquire(Integer id);

    List<DynaSquireDTO> query(DynaSquireDTO dto);

    DynaSquireDTO findByCondition(DynaSquireDTO dto);

    /**
     * 通过ID批量获取
     *
     * @param idSet
     * @return
     */
    List<DynaSquireDTO> batchGetSquireById(@Param("idSet") Set<Integer> idSet);

    /**
     * 通过最后ID和分页来获取，获取指定数量小于这个ID的集合
     *
     * @param lastId
     * @param pageSize
     * @return
     */
    List<DynaSquireDTO> findSquireByLastId(@Param("lastId") Integer lastId, @Param("userId") Integer userId,
                @Param("pageSize") Integer pageSize);

    /**
     * 通过最后ID和分页来获取，获取指定数量小于这个ID的集合
     *
     * @param lastId
     * @param pageSize
     * @return
     */
    List<DynaSquireDTO> findUserSquireByLastId(@Param("lastId") Integer lastId, @Param("pageSize") Integer pageSize,
                @Param("userId") Integer userId);

    /**
     * 通过最后ID和分页来获取，获取指定数量小于这个ID的集合
     *
     * @param lastId
     * @param pageSize
     * @return
     */
    List<DynaSquireDTO> findAttentionSquireByLastId(@Param("lastId") Integer lastId,
                @Param("pageSize") Integer pageSize, @Param("attentionUserIdSet") Set<Integer> attentionUserIdSet);

    /**
     * 统计用户动态数量
     *
     * @param userId
     * @return
     */
    Long countUserSquire(@Param("userId") Integer userId);

    /**
     * 查找发布过动态的用户列表，去重
     *
     * @return
     */
    List<DynaSquireDTO> findUserIdList();

    /**
     * 批量更新评论数
     *
     * @param list
     * @return
     */
    void batchUpdateCommentNum(@Param("list") List<DynaSquireDO> list);

    /**
     * 批量更新点赞数
     *
     * @param list
     * @return
     */
    void batchUpdateFavorNum(@Param("list") List<DynaSquireDO> list);

    /**
     * 统计回复的条数
     *
     * @param dynaSquireId
     * @return
     */
    @Select("SELECT num_reply FROM dyna_squire where dyna_squire_id = #{dynaSquireId}")
    Integer getNumReply(@Param(value = "dynaSquireId") Integer dynaSquireId);
}
