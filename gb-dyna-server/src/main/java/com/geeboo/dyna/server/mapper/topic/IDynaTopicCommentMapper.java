package com.geeboo.dyna.server.mapper.topic;

import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentListDTO;
import com.geeboo.dyna.server.entity.topic.DynaTopicCommentDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaTopicCommentMapper extends Mapper<DynaTopicCommentDO> {
    DynaTopicCommentDTO findById(Integer dynaTopicCommentId);

    int add(DynaTopicCommentDTO dto);

    int update(DynaTopicCommentDTO dto);

    int deleteDynaTopicComment(Integer id);

    List<DynaTopicCommentDTO> query(DynaTopicCommentDTO dto);

    DynaTopicCommentDTO findByCondition(DynaTopicCommentDTO dto);

    DynaTopicCommentListDTO getCommentDetail(@Param(value = "id") Integer id);

    /**
     * 根据话题ID和特殊帐号获取 ID倒序
     *
     * @param dynaTopicId
     * @param specialId   特殊ID
     * @param pageSize
     * @returna
     */
    List<DynaTopicCommentListDTO> getCommentByTopicAndSpecialId(@Param(value = "dynaTopicId") Integer dynaTopicId,
                                                                @Param(value = "specialId") Integer specialId, @Param(value = "pageSize") Integer pageSize);

    /**
     * 根据话题ID和上个ID获取 ID倒序
     *
     * @param dynaTopicId
     * @param lastId      从小于这个ID的主键开始查询
     * @param pageSize
     * @returna
     */
    List<DynaTopicCommentListDTO> getCommentByTopicAndLastId(@Param(value = "dynaTopicId") Integer dynaTopicId,
                                                             @Param(value = "lastId") Integer lastId, @Param(value = "specialId") Integer specialId,
                                                             @Param(value = "userId") Integer userId, @Param(value = "pageSize") Integer pageSize);

    List<DynaTopicCommentListDTO> getCommentByTopic(@Param(value = "dynaTopicId") Integer dynaTopicId,
                                                    @Param(value = "lastId") Integer lastId, @Param(value = "excludeIdList") List<String> excludeIdList,
                                                    @Param(value = "userId") Integer userId, @Param(value = "pageSize") Integer pageSize);

    List<DynaTopicCommentListDTO> getHotCommentByTopic(@Param(value = "dynaTopicId") Integer dynaTopicId, @Param(value = "specialId") Integer specialId,
                                                       @Param(value = "userId") Integer userId, @Param(value = "pageSize") Integer pageSize);

    /**
     * 获取最近评论的3个用户
     *
     * @param dynaTopicId
     * @return
     */
    List<DynaTopicCommentDTO> getRecentCommentUserList(@Param(value = "dynaTopicId") Integer dynaTopicId);

    /**
     * 统计评论的人数
     *
     * @param dynaTopicId
     * @return
     */
    Long getCommentCountGroupByAccount(@Param(value = "dynaTopicId") Integer dynaTopicId);

    /**
     * 批量更新评论回复计数
     *
     * @param list
     */
    void batchUpdateReplyNum(@Param(value = "list") List<DynaTopicCommentDO> list);

    /**
     * 批量更新评论点赞计数
     *
     * @param list
     */
    void batchUpdateFavorNum(@Param(value = "list") List<DynaTopicCommentDO> list);

    @Select("SELECT count(0)  FROM dyna_topic_comment  WHERE dyna_topic_id = #{dynaTopicId} and is_del = 0  and is_sensitive = 0 ;")
    int queryToppicCommentNum(@Param(value = "dynaTopicId") Integer dynaTopicId);
}
