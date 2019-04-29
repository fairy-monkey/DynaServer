package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;

public interface IDynaSquireService {

    /**
     * 分页查询
     *
     * @param dto  查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaSquireDTO> page(DynaSquireDTO dto, Page<DynaSquireDTO> page);



    /**
     * 删除动态_话题评论表
     *
     * @param dynaSquireId 系统主键
     * @return
     */
    BaseResponse delete(Integer dynaSquireId);




}