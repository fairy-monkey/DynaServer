package com.geeboo.dyna.server.client.facade.book;

import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.dyna.server.client.dto.book.DynaBookCommentFavorDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/18 13:54
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaBookFavorAppFacade {
    /**
     * 点赞
     *
     * @param dto
     * @return 是否是新增的且为点赞
     */
    @PostMapping(value = "/DynaServer/facade/dyna/book/favor/app/doFavor")
    ObjectResponse<Boolean> doFavor(@RequestBody DynaBookCommentFavorDTO dto);
}
