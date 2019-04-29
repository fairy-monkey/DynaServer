package com.geeboo.dyna.server.service.impl.sensitivestatistic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.senstivestatistic.SenstiveStatisticDTO;
import com.geeboo.dyna.server.mapper.sensitivestatistic.ISensitiveStatisticMapper;
import com.geeboo.dyna.server.service.sensitivestatistic.ISensitiveStatisticService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tangwei
 * @Title: SensitiveStatisticServiceImpl
 * @ProjectName DynaServer
 * @Description: 敏感词统计查询实现
 * @date 2018/11/30 16:26
 */
@Service
@Slf4j
public class SensitiveStatisticServiceImpl implements ISensitiveStatisticService {
    @Autowired
    private ISensitiveStatisticMapper sensitiveStatisticMapper;

    @Override
    public TableResultResponse getList(SenstiveStatisticDTO dto) {
        int startRow = (dto.getPageNo() - 1) * dto.getPageSize();
        int endRow = startRow + dto.getPageSize();
        List<Map<String, Object>> list = sensitiveStatisticMapper.getList(dto.getTableName(), dto.getFields(),
                    dto.getCreateTime(), startRow, endRow);
        TableResultResponse resp = new TableResultResponse(list.size(), dto.getPageNo(), list);
        return resp;
    }

    @Override
    public ObjectResponse getCount(SenstiveStatisticDTO dto) {
        int count = sensitiveStatisticMapper.getCount(dto.getTableName(), dto.getCreateTime());
        ObjectResponse resp = new ObjectResponse();
        resp.setData(count);
        return resp;
    }

    @Override
    public TableResultResponse getNoSensitiveList(SenstiveStatisticDTO dto) {
        int startRow = (dto.getPageNo() - 1) * dto.getPageSize();
        int endRow = startRow + dto.getPageSize();
        List<Map<String, Object>> list = sensitiveStatisticMapper.getNoSensitiveList(dto.getTableName(),
                    dto.getFields(), startRow, endRow);
        TableResultResponse resp = new TableResultResponse(list.size(), dto.getPageNo(), list);
        return resp;
    }

    @Override
    public ObjectResponse getNoSensitiveCount(SenstiveStatisticDTO dto) {
        int count = sensitiveStatisticMapper.getNoSensitiveCount(dto.getTableName());
        ObjectResponse resp = new ObjectResponse();
        resp.setData(count);
        return resp;
    }

    @Override
    @Transactional
    public BaseResponse update(SenstiveStatisticDTO dto) {
        sensitiveStatisticMapper.update(dto.getTableName(), dto.getPrikeyName(), dto.getPrikeyVaule());
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }
}
