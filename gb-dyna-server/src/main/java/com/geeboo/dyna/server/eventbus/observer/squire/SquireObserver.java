package com.geeboo.dyna.server.eventbus.observer.squire;

import com.geeboo.dyna.server.eventbus.dto.squire.SquireEventDTO;
import com.geeboo.dyna.server.service.squire.IDynaSquireWriteService;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/10/8 15:32
 */
public class SquireObserver {
    @Autowired
    private IDynaSquireWriteService dynaSquireWriteService;

    @Subscribe
    public void count(SquireEventDTO eventDTO) {
        if (eventDTO.getBusinessId() == null) {
            return;
        }
        if (Boolean.TRUE.equals(eventDTO.getSquireCount())) {
            dynaSquireWriteService.incrementSquireNum(eventDTO.getBusinessId(), eventDTO.getOperate());
        } else if (Boolean.TRUE.equals(eventDTO.getFavorNum())) {
            dynaSquireWriteService.incrementFavorNum(eventDTO.getBusinessId(), eventDTO.getOperate());
        } else if (Boolean.TRUE.equals(eventDTO.getCommentNum())) {
            dynaSquireWriteService.incrementCommentNum(eventDTO.getBusinessId(), eventDTO.getOperate());
        }
    }
}
