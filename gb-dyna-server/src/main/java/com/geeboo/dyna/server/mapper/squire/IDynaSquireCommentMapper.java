package com.geeboo.dyna.server.mapper.squire;

import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;
import com.geeboo.dyna.server.entity.squire.DynaSquireCommentDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IDynaSquireCommentMapper extends Mapper<DynaSquireCommentDO> {

    DynaSquireCommentDTO findById(Integer dynaSquireCommentId);

    int add(DynaSquireCommentDTO dto);

    int update(DynaSquireCommentDTO dto);

    int deleteDynaSquireComment(Integer id);



    /***
     * @Author tin
     * @date 2019/1/10
     * @Param [commentId]
     * @return int
     **/
    int deleteByDynaSquireId(@Param(value = "dynaSquireId") Integer dynaSquireId);


    /**
     * 根据最后ID获取评论列表
     *
     * @param dynaSquireId
     * @param lastId
     * @param pageSize
     * @return
     */
    List<DynaSquireCommentListDTO> getCommentBySquireAndLastId(@Param("dynaSquireId") Integer dynaSquireId,
                                                               @Param("lastId") Integer lastId, @Param("userId") Integer userId, @Param("pageSize") Integer pageSize);

    /**
     * 获取评论详情
     *
     * @param id
     * @return
     */
    DynaSquireCommentListDTO getCommentDetail(@Param("id") Integer id);

    /**
     * 统计评论数量
     *
     * @param squireId
     * @return
     */
    Long countCommentBySquire(@Param("squireId") Integer squireId);


    List<DynaSquireCommentListDTO> getCommentListByLastId(@Param("dynaSquireId") Integer dynaSquireId,
                                                          @Param("lastId") Integer lastId, @Param("pageSize") Integer pageSize);

    List<DynaSquireCommentListDTO> query(DynaSquireCommentListDTO dto);

    DynaSquireCommentListDTO findByCondition(DynaSquireCommentListDTO dto);


}
