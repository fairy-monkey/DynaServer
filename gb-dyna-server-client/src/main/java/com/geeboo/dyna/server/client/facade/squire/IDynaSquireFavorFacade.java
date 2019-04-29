package com.geeboo.dyna.server.client.facade.squire;

import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireFavorRequestDTO;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireUserFavorRequestDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaSquireFavorFacade {
    /**
     * 点赞
     *
     * @param dto 待新增的实体对象
     * @return 是否是新增的且为点赞
     */
    @RequestMapping(value = "/DynaServer/facade/dyna/favor/app/doFavor", method = RequestMethod.POST)
    ObjectResponse<Boolean> doFavor(@RequestBody DynaSquireFavorRequestDTO dto);

    /**
     * 从广场ID集合中查找出用户点赞的广场ID
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/favor/app/findSquireFavorList")
    ObjectResponse<Set<Integer>> findSquireFavorList(@RequestBody DynaSquireUserFavorRequestDTO dto);

    /**
     * 获取点赞数据
     */
    @PostMapping(value = "/DynaServer/facade/dyna/favor/app/getDynaSquireFavorDTO")
    ObjectResponse<DynaSquireFavorDTO> getDynaSquireFavorDTO(@RequestBody DynaSquireFavorDTO dto);

}

