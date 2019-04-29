package com.geeboo.dyna.server.mapper.book;

import com.geeboo.dyna.server.client.dto.book.DynaBookCommentDTO;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentListDTO;
import com.geeboo.dyna.server.entity.book.DynaBookCommentDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaBookCommentMapper extends Mapper<DynaBookCommentDO> {
    DynaBookCommentDTO findById(Integer dynaBookCommentId);

    int add(DynaBookCommentDTO dto);

    int update(DynaBookCommentDTO dto);

    int deleteDynaBookComment(Integer id);

    List<DynaBookCommentDTO> query(DynaBookCommentDTO dto);

    DynaBookCommentDTO findByCondition(DynaBookCommentDTO dto);

    DynaBookCommentListDTO getCommentDetail(@Param(value = "id") Integer id);

    /**
     * 根据话题ID和特殊帐号获取 ID倒序
     *
     * @param bookUserId
     * @param specialId 特殊ID
     * @param pageSize
     * @returna
     */
    List<DynaBookCommentListDTO> getCommentByBookUserAndSpecialId(@Param(value = "bookUserId") Integer bookUserId,
                @Param(value = "specialId") Integer specialId, @Param(value = "pageSize") Integer pageSize);

    /**
     * 根据话题ID和上个ID获取 ID倒序
     *
     * @param bookUserId
     * @param lastId 从小于这个ID的主键开始查询
     * @param pageSize
     * @returna
     */
    List<DynaBookCommentListDTO> getCommentByBookUserAndLastId(@Param(value = "bookUserId") Integer bookUserId,
                @Param(value = "lastId") Integer lastId, @Param(value = "specialId") Integer specialId,
                @Param(value = "userId") Integer userId, @Param(value = "pageSize") Integer pageSize);

    /**
     * 获取最近评论的3个用户
     *
     * @param bookUserId
     * @return
     */
    List<DynaBookCommentDTO> getRecentCommentUserList(@Param(value = "bookUserId") Integer bookUserId);

    /**
     * 统计评论的人数
     *
     * @param bookUserId
     * @return
     */
    Long getCommentCountGroupByAccount(@Param(value = "bookUserId") Integer bookUserId);

    /**
     * 批量更新评论回复计数
     *
     * @param list
     */
    void batchUpdateReplyNum(@Param(value = "list") List<DynaBookCommentDO> list);

    /**
     * 批量更新评论点赞计数
     *
     * @param list
     */
    void batchUpdateFavorNum(@Param(value = "list") List<DynaBookCommentDO> list);
}
