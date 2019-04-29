package com.geeboo.dyna.server.controller.squire;

import com.geeboo.auth.client.annotation.IgnoreClientToken;
import com.geeboo.auth.client.annotation.IgnoreUserToken;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorRequestDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireUserFavorRequestDTO;
import com.geeboo.dyna.server.service.squire.IDynaSquireFavorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@IgnoreUserToken
@IgnoreClientToken
@Api(tags = "动态_广场点赞表相关接口}")
@RestController
@RequestMapping("/facade/dyna/favor/app")
public class DynaSquireFavorAppController {
    @Autowired
    private IDynaSquireFavorService dynaSquireFavorService;

    /**
     * 增加动态_广场点赞表
     *
     * @param dto 需要插入的实体
     * @return
     */
    @RequestMapping(value = "/doFavor", method = RequestMethod.POST)
    ObjectResponse<Boolean> doFavor(@RequestBody DynaSquireFavorRequestDTO dto) {
        return dynaSquireFavorService.doFavor(dto);
    }

    /**
     * 从广场ID集合中查找出用户点赞的广场ID
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/findSquireFavorList")
    ObjectResponse<Set<Integer>> findSquireFavorList(@RequestBody DynaSquireUserFavorRequestDTO dto) {
        return dynaSquireFavorService.findSquireFavorList(dto.getSquireIdSet(), dto.getUserId());
    }


    /**
     * 获取点赞数据
     */
    @PostMapping(value = "/getDynaSquireFavorDTO")
    ObjectResponse<DynaSquireFavorDTO> getDynaSquireFavorDTO(@RequestBody DynaSquireFavorDTO dto) {
        return dynaSquireFavorService.getDynaSquireFavorDTO(dto);
    }

}
