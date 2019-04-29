package com.geeboo.dyna.server.controller.squire;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.squire.*;
import com.geeboo.dyna.server.service.squire.IDynaSquireReadService;
import com.geeboo.dyna.server.service.squire.IDynaSquireWriteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场信息表相关接口}")
@RestController
@RequestMapping("/facade/dyna")
public class DynaSquireAppController {
    @Autowired
    private IDynaSquireWriteService dynaSquireWriteService;
    @Autowired
    private IDynaSquireReadService dynaSquireReadService;

    /**
     * 修改动态_广场信息表
     *
     * @param dto 需要修改的实体
     * @return
     */
    @RequestMapping(value = "/squire/app", method = RequestMethod.PUT)
    BaseResponse update(@RequestBody DynaSquireDTO dto) {
        return dynaSquireWriteService.update(dto);
    }

    /**
     * 删除动态_广场信息表
     *
     * @param dto 包含系统主键&创建人
     * @return
     */
    @RequestMapping(value = "/squire/app", method = RequestMethod.DELETE)
    BaseResponse delete(@RequestBody DynaSquireDTO dto) {
        return dynaSquireWriteService.delete(dto);
    }

    /**
     * 分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/squire/app/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireDTO> page(@RequestBody PageRestRequest<DynaSquireDTO> pageRestRequest) {
        return dynaSquireReadService.page(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 关注列表分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/squire/app/attention/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireDTO> attentionPage(@RequestBody PageRestRequest<DynaAttentionSquireDTO> pageRestRequest) {
        return dynaSquireReadService.attentionPage(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 关注列表分页查询
     *
     * @param pageRestRequest 分页条件，当前页，每页显示条数
     * @return
     */
    @RequestMapping(value = "/squire/app/user/page", method = RequestMethod.POST)
    TableResultResponse<DynaSquireDTO> userPage(@RequestBody PageRestRequest<DynaSquireDTO> pageRestRequest) {
        return dynaSquireReadService.userPage(pageRestRequest.getData(), pageRestRequest.getPage());
    }

    /**
     * 通过条件获取动态_广场信息表实体
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/squire/app/findByCondition", method = RequestMethod.POST)
    ObjectResponse<DynaSquireDTO> findByCondition(@RequestBody DynaSquireDTO dto) {
        return dynaSquireReadService.findByCondition(dto);
    }

    /**
     * 通过ID获取动态_广场信息表实体
     *
     * @param id 主键ID
     * @return
     */
    @RequestMapping(value = "/squire/app/detail/{id}", method = RequestMethod.GET)
    ObjectResponse<DynaSquireDTO> findDetailById(@PathVariable(value = "id") Integer id) {
        return dynaSquireReadService.findDetailById(id);
    }

    @RequestMapping(value = "/squire/app/findSquireDetail", method = RequestMethod.POST)
    ObjectResponse<DynaSquireDTO> findSquireDetail(@RequestBody DynaSquireFavorDTO dto) {
        return dynaSquireReadService.findSquireDetail(dto);
    }

    /**
     * 通过ID获取动态_广场信息表实体
     *
     * @param id 主键ID
     * @return
     */
    @RequestMapping(value = "/squire/app/{id}", method = RequestMethod.GET)
    ObjectResponse<DynaSquireDTO> findById(@PathVariable(value = "id") Integer id) {
        return dynaSquireReadService.findById(id);
    }

    /**
     * 通过笔记增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/squire/app/note")
    ObjectResponse<DynaSquireNoteDTO> addSquireFromNote(@RequestBody DynaSquireNoteDTO dto) {
        return dynaSquireWriteService.addSquireFromNote(dto);
    }

    /**
     * 通过书增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/squire/app/book")
    ObjectResponse<DynaSquireBookDTO> addSquireFromBook(@RequestBody DynaSquireBookDTO dto) {
        return dynaSquireWriteService.addSquireFromBook(dto);
    }

    /**
     * 通过读后感增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/squire/app/readBook")
    ObjectResponse<DynaSquireReadBookDTO> addSquireFromReadBook(@RequestBody DynaSquireReadBookDTO dto) {
        return dynaSquireWriteService.addSquireFromReadBook(dto);
    }

    /**
     * 通过书摘增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/squire/app/shareBook")
    ObjectResponse<DynaSquireBookDTO> addSquireFromShareBook(@RequestBody DynaSquireBookDTO dto) {
        return dynaSquireWriteService.addSquireFromShareBook(dto);
    }

    /**
     * 通过书摘增加
     *
     * @param dto 待新增的实体对象
     * @return
     */
    @PostMapping(value = "/squire/app/record")
    ObjectResponse<DynaSquireBookDTO> addSquireFromRecord(@RequestBody DynaSquireRecordDTO dto) {
        return dynaSquireWriteService.addSquireFromRecord(dto);
    }

    /**
     * 初始化用户广场统计
     *
     * @param userIdSet 要初始化的用户ID集合，空则初始化所有
     * @return
     */
    @PostMapping(value = "/squire/app/initializeSquireNum")
    BaseResponse initializeSquireNum(@RequestBody Set<Integer> userIdSet) {
        return dynaSquireWriteService.initializeSquireNum(userIdSet);
    }

    /**
     * 初始化广场点赞统计
     *
     * @param dynaSquireIdSet 要初始化的广场ID集合，空则初始化所有
     * @return
     */
    @PostMapping(value = "/squire/app/initializeFavorNum")
    BaseResponse initializeFavorNum(@RequestBody Set<Integer> dynaSquireIdSet) {
        return dynaSquireWriteService.initializeFavorNum(dynaSquireIdSet);
    }

    /**
     * 初始化广场评论统计
     *
     * @param dynaSquireIdSet 要初始化的广场ID集合，空则初始化所有
     * @return
     */
    @PostMapping(value = "/squire/app/initializeCommentNum")
    BaseResponse initializeCommentNum(@RequestBody Set<Integer> dynaSquireIdSet) {
        return dynaSquireWriteService.initializeCommentNum(dynaSquireIdSet);
    }

    /**
     * 获取某个用户的动态总数
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping(value = "/squire/app/userSquireNum/{userId}")
    ObjectResponse<Long> getUserSquireNum(@PathVariable("userId") Integer userId) {
        return dynaSquireReadService.getUserSquireNum(userId);
    }

    /**
     * 通过条件获取动态_广场信息列表
     *
     * @param dto 查询条件
     * @return
     */
    @RequestMapping(value = "/squire/app/findByConditionList", method = RequestMethod.POST)
    TableResultResponse<DynaSquireDTO> findByConditionList(@RequestBody DynaSquireDTO dto) {
        return dynaSquireReadService.findByConditionList(dto);
    }

}
