package com.geeboo.dyna.server.service.book;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.book.DynaBookComplaintDTO;

public interface IDynaBookComplaintService {
    /**
     * 增加动态_图书举报表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaBookComplaintDTO dto);

    /**
     * 修改动态_图书举报表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaBookComplaintDTO dto);

    /**
     * 删除动态_图书举报表
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
    TableResultResponse<DynaBookComplaintDTO> page(DynaBookComplaintDTO dto, Page<DynaBookComplaintDTO> page);

    /**
     * 通过ID获取动态_图书举报表实体
     *
     * @param id 主键id
     * @return
     */
    ObjectResponse findById(Integer id);

    /**
     * 通过条件获取动态_图书举报表实体
     *
     * @param dto 查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaBookComplaintDTO dto);
}