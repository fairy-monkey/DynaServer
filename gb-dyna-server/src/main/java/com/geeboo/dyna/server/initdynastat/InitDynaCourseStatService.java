package com.geeboo.dyna.server.initdynastat;

import com.geeboo.dyna.server.client.dto.course.DynaCourseStatDTO;
import com.geeboo.dyna.server.mapper.course.IDynaCourseCommentMapper;
import com.geeboo.dyna.server.mapper.course.IDynaCourseStatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InitDynaCourseStatService implements Runnable {
    @Autowired
    private IDynaCourseCommentMapper dynaCourseCommentMapper;
    @Autowired
    private IDynaCourseStatMapper courseStatMapper;

    @Override
    public void run() {
        DynaCourseStatDTO dynaCourseStatDTO = new DynaCourseStatDTO();
        List<DynaCourseStatDTO> query = courseStatMapper.query(dynaCourseStatDTO);
        for (DynaCourseStatDTO dto : query) {
            // 评论数
            Integer numComment = dynaCourseCommentMapper.getCommentCountNumByCourseId(dto.getCourseId());
            dto.setNumComment(numComment);
            courseStatMapper.update(dto);
        }

    }
}
