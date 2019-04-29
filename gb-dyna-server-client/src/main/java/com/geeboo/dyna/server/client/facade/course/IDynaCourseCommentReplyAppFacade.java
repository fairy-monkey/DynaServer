package com.geeboo.dyna.server.client.facade.course;

import com.geeboo.common.msg.BaseResponse;
import com.geeboo.common.msg.ObjectResponse;
import com.geeboo.common.msg.PageRestRequest;
import com.geeboo.common.msg.TableResultResponse;
import com.geeboo.dyna.server.client.dto.course.DynaCourseCommentReplyDTO;
import com.geeboo.dyna.server.client.dto.course.DynaCourseReplyListDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/15 18:08
 */
@FeignClient(value = "${gb-dyna-server}", configuration = {})
public interface IDynaCourseCommentReplyAppFacade {
    /**
     * 获取评论分页
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/course/reply/app/getReplyPage")
    TableResultResponse<DynaCourseReplyListDTO> getReplyPage(@RequestBody PageRestRequest<DynaCourseReplyListDTO> page);

    /**
     * 添加回复 返回新增回复主键ID，被回复者ID 如果是回复评论，被回复者ID为0，需要自行查找
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/DynaServer/facade/dyna/course/reply/app/reply")
    ObjectResponse<DynaCourseCommentReplyDTO> addReply(@RequestBody DynaCourseCommentReplyDTO dto);

    /**
     * 删除回复
     *
     * @param dto
     * @return
     */
    @DeleteMapping(value = "/DynaServer/facade/dyna/course/reply/app/reply")
    BaseResponse deleteReply(@RequestBody DynaCourseCommentReplyDTO dto);

    @RequestMapping(value = "/DynaServer/facade/dyna/course/reply/app/update", method = RequestMethod.POST)
    BaseResponse updateReply(@RequestBody DynaCourseCommentReplyDTO dto);
}
