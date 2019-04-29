package com.geeboo.dyna.server.initdynastat;

import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicStatDTO;
import com.geeboo.dyna.server.service.topic.IDynaTopicCommentService;
import com.geeboo.dyna.server.service.topic.IDynaTopicStatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InitDynaTopicStatService implements Runnable {
    @Autowired
    private IDynaTopicCommentService dynaTopicCommentService;
    @Autowired
    private IDynaTopicStatService dynaTopicStatService;

    @Override
    public void run() {
        DynaTopicStatDTO dynaTopicStatDTO = new DynaTopicStatDTO();
        TableResultResponse<DynaTopicStatDTO> query = dynaTopicStatService.query(dynaTopicStatDTO);
        int size = query.getData().getRows().size();
        log.info("总数：" + size);
        for (DynaTopicStatDTO dto : query.getData().getRows()) {
            // numComment 参与评论数
            int numComment = dynaTopicCommentService.queryToppicCommentNum(dto.getDynaTopicId());
            dto.setNumComment(numComment);
            // 最近3个用户
            List<DynaTopicCommentDTO> recentCommentUserList = dynaTopicCommentService.getRecentCommentUserList(dto.getDynaTopicId());
            this.setRecentCommentUser(dto, recentCommentUserList);
            dynaTopicStatService.update(dto);
            log.info("还剩：" + size--);
        }
    }

    /**
     * 最近三个用户设置
     *
     * @param dto
     * @param recentCommentUserList
     */
    private void setRecentCommentUser(DynaTopicStatDTO dto, List<DynaTopicCommentDTO> recentCommentUserList) {
        if (recentCommentUserList.size() == 3) {
            dto.setThirdUserId(recentCommentUserList.get(2).getUserId());
            dto.setSecondUserId(recentCommentUserList.get(1).getUserId());
            dto.setFirstUserId(recentCommentUserList.get(0).getUserId());
        }
        if (recentCommentUserList.size() == 2) {
            dto.setThirdUserId(0);
            dto.setSecondUserId(recentCommentUserList.get(1).getUserId());
            dto.setFirstUserId(recentCommentUserList.get(0).getUserId());
        }
        if (recentCommentUserList.size() == 1) {
            dto.setThirdUserId(0);
            dto.setSecondUserId(0);
            dto.setFirstUserId(recentCommentUserList.get(0).getUserId());
        }
        if (recentCommentUserList.size() == 0) {
            dto.setThirdUserId(0);
            dto.setSecondUserId(0);
            dto.setFirstUserId(0);
        }
    }
}
