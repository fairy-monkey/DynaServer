package com.geeboo.dyna.server.service.impl.digest;

import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.common.qiniu.QiniuConfiguration;
import com.geeboo.common.util.CheckUtils;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestFavorDTO;
import com.geeboo.dyna.server.client.dto.digest.DynaDigestListDTO;
import com.geeboo.dyna.server.mapper.digest.IDynaDigestMapper;
import com.geeboo.dyna.server.service.digest.IDynaDigestAppService;
import com.geeboo.dyna.server.service.digest.IDynaDigestFavorAppService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Slf4j
@Service
public class DynaDigestAppServiceImpl implements IDynaDigestAppService {
    @Autowired
    private IDynaDigestMapper dynaDigestMapper;

    @Autowired
    private IDynaDigestFavorAppService digestFavorAppService;

    @Autowired
    private QiniuConfiguration qiniuConfiguration;

    /**
     * @description：获取书摘列表
     * @author：luozh
     * @date：2018/9/27 15:58
     */
    @Override
    public TableResultResponse<DynaDigestListDTO> getDigestPage(PageRestRequest<DynaDigestListDTO> page) {
        PageHelper.startPage(page.getPage().getPageNo(), page.getPage().getPageSize());
        Page<DynaDigestListDTO> list = dynaDigestMapper.getDigestPage(page.getData());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(dto -> dto.setImagePath(qiniuConfiguration.joinBookBucketDomain(dto.getImagePath())));
        }
        TableResultResponse response = new TableResultResponse<>(list.getTotal(), list.getPages(), list.getResult());
        Integer userId = page.getData().getUserId();
        if (list.isEmpty() || !CheckUtils.id(userId)) {
            return response;
        }
        DynaDigestFavorDTO favorDTO = new DynaDigestFavorDTO();
        favorDTO.setUserId(userId);
        Set<Integer> favorSet = digestFavorAppService.getDigestIdsByUserId(favorDTO);
        for (DynaDigestListDTO dto : list) {
            if (!favorSet.isEmpty() && favorSet.contains(dto.getDynaDigestId())) {
                dto.setIsFavor(1);
            } else {
                dto.setIsFavor(0);
            }
        }
        return response;
    }

    /**
     * @description：更新点赞数
     * @author：luozh
     * @date：2018/9/29 10:19
     */
    @Override
    public void incrementNumSupport(Integer dynaDigestId, Integer isFavor) {
        dynaDigestMapper.incrementNumSupport(dynaDigestId, isFavor);
    }
}
