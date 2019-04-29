package com.geeboo.dyna.server.service.digest;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestDTO;

public interface IDynaDigestService {
    /**
     * 增加动态_书摘表
     *
     * @param dto
     * @return
     */
    BaseResponse add(DynaDigestDTO dto);

    /**
     * 修改动态_书摘表
     *
     * @param dto
     * @return
     */
    BaseResponse update(DynaDigestDTO dto);

    /**
     * 删除动态_书摘表
     *
     * @param dynaDigestId 系统主键
     * @return
     */
    BaseResponse remove(Integer dynaDigestId);

    /**
     * 分页查询
     *
     * @param dto 查询条件
     * @param page 当前页,每页显示的条数
     * @return
     */
    TableResultResponse<DynaDigestDTO> page(DynaDigestDTO dto, Page<DynaDigestDTO> page);

    /**
     * 普通查询
     *
     * @param dto 查询条件
     * @return
     */
    TableResultResponse<DynaDigestDTO> query(DynaDigestDTO dto);

    /**
     * 通过ID获取动态_书摘表实体
     *
     * @param dynaDigestId ：主键id
     * @return
     */
    ObjectResponse findById(Integer dynaDigestId);

    /**
     * 通过条件获取动态_书摘表实体
     *
     * @param dto ：查询条件
     * @return
     */
    ObjectResponse findByCondition(DynaDigestDTO dto);

    /**
     * 发布
     *
     * @param dto
     * @return
     */
    BaseResponse publish(DynaDigestDTO dto);

    /**
     * 取消发布
     *
     * @param dto
     * @return
     */
    BaseResponse off(DynaDigestDTO dto);

    /**
     * 定时发布
     * @param dto
     * @return
     */
    BaseResponse timeToPublish(DynaDigestDTO dto);
}