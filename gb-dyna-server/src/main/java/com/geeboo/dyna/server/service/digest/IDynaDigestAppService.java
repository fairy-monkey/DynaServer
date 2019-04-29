package com.geeboo.dyna.server.service.digest;


import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestListDTO;

public interface IDynaDigestAppService {
    /**
     * @description：书摘列表
     * @author：luozh
     * @date：2018/9/28 15:57
     */
    TableResultResponse<DynaDigestListDTO> getDigestPage(PageRestRequest<DynaDigestListDTO> page);

    /**
     * @description：更新点赞数
     * @author：luozh
     * @date：2018/9/29 10:17
     */
    void incrementNumSupport(Integer dynaDigestId, Integer isFavor);
}