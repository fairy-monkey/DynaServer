package com.geeboo.dyna.server.client.facade.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireBookDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireNoteDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireRecordDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireWriteFacade {
    /**
     * 通过笔记增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/note")
    ObjectResponse<DynaSquireNoteDTO> addSquireFromNote(@RequestBody DynaSquireNoteDTO dto);

    /**
     * 通过书增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/book")
    ObjectResponse<DynaSquireBookDTO> addSquireFromBook(@RequestBody DynaSquireBookDTO dto);

    /**
     * 通过书摘增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/shareBook")
    ObjectResponse<DynaSquireBookDTO> addSquireFromShareBook(@RequestBody DynaSquireBookDTO dto);

    /**
     * 通过分享档案增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/record")
    ObjectResponse<DynaSquireBookDTO> addSquireFromRecord(DynaSquireRecordDTO dto);

    /**
     * 删除
     *
     * @param dto 系统主键&创建人
     * @return
     */
    @DeleteMapping(value = "/DynaServer/facade/dyna/squire/app")
    BaseResponse delete(@RequestBody DynaSquireDTO dto);

    /**
     * 初始化用户广场统计
     *
     * @param userIdSet 要初始化的用户ID集合，空则初始化所有
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/initializeSquireNum")
    BaseResponse initializeSquireNum(@RequestBody Set<Integer> userIdSet);

    /**
     * 初始化广场点赞统计
     *
     * @param dynaSquireIdSet 要初始化的广场ID集合，空则初始化所有
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/initializeFavorNum")
    BaseResponse initializeFavorNum(@RequestBody Set<Integer> dynaSquireIdSet);

    /**
     * 初始化广场评论统计
     *
     * @param dynaSquireIdSet 要初始化的广场ID集合，空则初始化所有
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/squire/app/initializeCommentNum")
    BaseResponse initializeCommentNum(@RequestBody Set<Integer> dynaSquireIdSet);

    /**
     * 更新动态广场
     *
     * @param dto 系统主键&创建人
     * @return
     */
    @PutMapping(value = "/DynaServer/facade/dyna/squire/app")
    BaseResponse update(@RequestBody DynaSquireDTO dto);

    @RequestMapping(value = "/DynaServer/facade/dyna/squire/app/detail/findById", method = RequestMethod.POST)
    ObjectResponse<DynaSquireDTO> findById(@RequestBody DynaSquireDTO dto);
}
