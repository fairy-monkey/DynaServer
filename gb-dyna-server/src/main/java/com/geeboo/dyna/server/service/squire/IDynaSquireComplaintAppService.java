package com.geeboo.dyna.server.service.squire;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireComplaintDTO;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 林诗炀 linsy 创建时间:2018/11/19 10:09
 */
public interface IDynaSquireComplaintAppService {
    /**
     * 举报广场
     *
     * @param dto
     * @return
     */
    BaseResponse complaint(DynaSquireComplaintDTO dto);
}
