package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireComplaintDTO;

public interface IDynaSquireComplaintService {
    /**
     * 增加动态_广场举报表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaSquireComplaintDTO dto);

    /**
     * 修改动态_广场举报表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaSquireComplaintDTO dto);

    /**
     * 删除动态_广场举报表
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
    TableResultResponse<DynaSquireComplaintDTO> page(DynaSquireComplaintDTO dto, Page<DynaSquireComplaintDTO> page);

    /**
     * 通过ID获取动态_广场举报表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_广场举报表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaSquireComplaintDTO dto);
}