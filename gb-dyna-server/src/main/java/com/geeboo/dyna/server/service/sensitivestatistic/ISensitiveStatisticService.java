package com.geeboo.dyna.server.service.sensitivestatistic;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.senstivestatistic.SenstiveStatisticDTO;

/**
 * @author tangwei
 * @Title: ISensitiveStatisticService
 * @ProjectName DynaServer
 * @Description:
 * @date 2018/11/30 16:24
 */
public interface ISensitiveStatisticService {
    /**
     * 获取敏感词列表
     * 
     * @param dto
     * @return
     */
    TableResultResponse getList(SenstiveStatisticDTO dto);

    /**
     * 获取数量
     * 
     * @param dto
     * @return
     */
    ObjectResponse getCount(SenstiveStatisticDTO dto);

    /**
     * 获取非敏感词列表
     *
     * @param dto
     * @return
     */
    TableResultResponse getNoSensitiveList(SenstiveStatisticDTO dto);

    /**
     * 获取非敏感词数量
     *
     * @param dto
     * @return
     */
    ObjectResponse getNoSensitiveCount(SenstiveStatisticDTO dto);

    /**
     * 更新为包含敏感词
     * 
     * @param dto
     * @return
     */
    BaseResponse update(SenstiveStatisticDTO dto);
}
