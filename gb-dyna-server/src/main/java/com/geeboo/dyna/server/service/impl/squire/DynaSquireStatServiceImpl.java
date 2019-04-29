package com.geeboo.dyna.server.service.impl.squire;

import com.geeboo.common.constant.StatusEnum;
import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.page.Page;
import com.geeboo.dyna.server.client.dto.squire.DynaSquireStatDTO;
import com.geeboo.dyna.server.mapper.squire.IDynaSquireStatMapper;
import com.geeboo.dyna.server.service.squire.IDynaSquireStatService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DynaSquireStatServiceImpl implements IDynaSquireStatService {
    @Autowired
    private IDynaSquireStatMapper dynaSquireStatMapper;

    @Transactional
    @Override
    public BaseResponse add(DynaSquireStatDTO dto) {
        dynaSquireStatMapper.add(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse update(DynaSquireStatDTO dto) {
        dynaSquireStatMapper.update(dto);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Transactional
    @Override
    public BaseResponse delete(Integer dynaSquireStatId) {
        dynaSquireStatMapper.deleteDynaSquireStat(dynaSquireStatId);
        return BaseResponse.success(StatusEnum.SUCCESS.getDescribe());
    }

    @Override
    public TableResultResponse<DynaSquireStatDTO> page(DynaSquireStatDTO dto, Page<DynaSquireStatDTO> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DynaSquireStatDTO> list = dynaSquireStatMapper.query(dto);
        com.github.pagehelper.Page<DynaSquireStatDTO> result = (com.github.pagehelper.Page<DynaSquireStatDTO>) list;
        TableResultResponse<DynaSquireStatDTO> response =
                new TableResultResponse<>(result.getTotal(), result.getResult());
        return response;
    }

    @Override
    public ObjectResponse<DynaSquireStatDTO> findById(Integer id) {
        DynaSquireStatDTO dto = dynaSquireStatMapper.findById(id);
        if (dto != null) {
            ObjectResponse<DynaSquireStatDTO> response = new ObjectResponse<>();
            return response.data(dto);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }

    @Override
    public ObjectResponse<DynaSquireStatDTO> findByCondition(DynaSquireStatDTO dto) {
        DynaSquireStatDTO model = dynaSquireStatMapper.findByCondition(dto);
        if (null != model) {
            ObjectResponse<DynaSquireStatDTO> response = new ObjectResponse<>();
            return response.data(model);
        } else {
            return ObjectResponse.failure(StatusEnum.FAILURE.getStatus(), StatusEnum.FAILURE.getDescribe());
        }
    }
}


