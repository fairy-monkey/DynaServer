package com.geeboo.dyna.server.service.course;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseComplaintDTO;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/19 11:03
 */
public interface IDynaCourseComplaintAppService {
    /**
     * 举报评论
     *
     * @param dto
     * @return
     */
    BaseResponse complaint(DynaCourseComplaintDTO dto);
}
