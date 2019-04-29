package com.geeboo.dyna.server.client.facade.squire;

import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaAttentionSquireDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/29 14:33
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireReadFacade {
    /**
     * 广场分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/page")
    TableResultResponse<DynaSquireDTO> getSquirePage(@RequestBody PageRestRequest<DynaSquireDTO> page);

    /**
     * 广场特别关注分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/attention/page")
    TableResultResponse<DynaSquireDTO> getSquireAttentionPage(@RequestBody PageRestRequest<DynaAttentionSquireDTO> page);

    /**
     * 广场特别关注分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/user/page")
    TableResultResponse<DynaSquireDTO> getSquireUserPage(@RequestBody PageRestRequest<DynaSquireDTO> page);

    /**
     * 根据ID查询详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/DynaServer/facade/dyna/squire/app/detail/{id}")
    ObjectResponse<DynaSquireDTO> findDetailById(@PathVariable(value = "id") Integer id);

    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/findSquireDetail")
    ObjectResponse<DynaSquireDTO> findSquireDetail(@RequestBody DynaSquireFavorDTO dto);

    /**
     * 通过ID获取动态_广场信息表实体
     *
     * @param dto 主键ID
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/findById")
    ObjectResponse<DynaSquireDTO> findById(@RequestBody DynaSquireDTO dto);

    /**
     * 获取某个用户的动态总数
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping(value = "/DynaServer/facade/dyna/squire/app/userSquireNum/{userId}")
    ObjectResponse<Long> getUserSquireNum(@PathVariable("userId") Integer userId);


    /**
     * 通过条件获取动态_广场信息表实体
     *
     * @param dto 查询条件
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/findByCondition")
    ObjectResponse<DynaSquireDTO> findByCondition(@RequestBody DynaSquireDTO dto);


    /**
     * 通过条件获取动态_广场信息列表
     *
     * @param dto 查询条件
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/findByConditionList")
    TableResultResponse<DynaSquireDTO> findByConditionList(@RequestBody DynaSquireDTO dto);


}
