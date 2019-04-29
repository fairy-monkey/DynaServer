package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;

public interface IDynaSquireCommentAppService {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    TableResultResponse<DynaSquireCommentListDTO> page(DynaSquireCommentListDTO dto,
                                                       Page<DynaSquireCommentListDTO> page);

    /**
     * 根据ID获取详情
     *
     * @param id
     * @return
     */
    DynaSquireCommentListDTO getCommentDetail(Integer id);

    /**
     * 添加评论
     *
     * @param dto
     * @return
     */
    ObjectResponse<DynaSquireCommentDTO> addComment(DynaSquireCommentDTO dto);

    /**
     * 删除评论
     *
     * @param dto
     * @return
     */
    BaseResponse deleteComment(DynaSquireCommentDTO dto);

    /**
     * 删除评论(运营管理后台)
     *
     * @param dto
     * @return
     */
    BaseResponse deleteComment(DynaSquireCommentListDTO dto);


    /**
     * 统计评论数量
     *
     * @param squireId
     * @return
     */
    Long countCommentBySquire(Integer squireId);

    BaseResponse updateComment(DynaSquireCommentDTO dto);

    ObjectResponse<DynaSquireCommentDTO> getById(DynaSquireCommentDTO dto);
}
