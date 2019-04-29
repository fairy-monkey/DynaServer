package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireCommentListDTO;

public interface IDynaSquireCommentService {
    /**
     * 评论分页
     *
     * @param page
     * @return
     */
    TableResultResponse<DynaSquireCommentListDTO> page(DynaSquireCommentListDTO dto,
                                                       Page<DynaSquireCommentListDTO> page);


    /**
     * 根据动态主键删除
     *
     * @param dynaSquireId
     * @return
     */
    BaseResponse deleteByDynaSquireId(Integer dynaSquireId);


    /**
     * 删除评论(运营管理后台)
     *
     * @param dto
     * @return
     */
    BaseResponse delete(DynaSquireCommentListDTO dto);




}
