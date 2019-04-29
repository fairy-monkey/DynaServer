package com.geeboo.dyna.server.service.topic;

import com.geeboo.common.exception.runtime.ServiceException;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicDTO;
import com.geeboo.dyna.server.client.dto.topic.DynaTopicListAndUserDTO;
import com.geeboo.dyna.server.constant.OperateEnum;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/12 13:56
 */
public interface IDynaTopicAppService {
    /**
     * 话题分页
     *
     * @param page
     * @return
     */
    ObjectResponse<DynaTopicListAndUserDTO> getTopicPage(PageRestRequest<DynaTopicDTO> page);

    /**
     * 查找首页话题
     * @return
     */
    ObjectResponse<DynaTopicDTO> findLatestTopic();

    /**
     * 话题相关缓存计数自增
     *
     * @param dto
     * @param operate 新增还是删除
     * @throws ServiceException
     */
    void incrementTopicCount(DynaTopicCommentDTO dto, OperateEnum operate) throws ServiceException;

    /**
     * 删除评论时，减去回复数
     *
     * @param dto
     * @param count
     * @throws ServiceException
     */
    void incrementTopicCountReply(DynaTopicCommentDTO dto, Long count) throws ServiceException;

    /**
     * 话题相关缓存计数自增
     *
     * @param dto
     * @param operate 新增还是删除
     * @throws ServiceException
     */
    void incrementTopicCount(DynaTopicCommentReplyDTO dto, OperateEnum operate) throws ServiceException;
}
