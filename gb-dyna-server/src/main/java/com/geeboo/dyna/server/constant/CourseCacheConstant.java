package com.geeboo.dyna.server.constant;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/21 14:22
 */
public class CourseCacheConstant {
    /**
     * 图书评论数，后面加ID
     */
    public static final String COURSE_COMMENT_NUM = "course:commentNum:";
    /**
     * 图书评论评论锁，后面加ID
     */
    public static final String COURSE_COMMENT_LOCK_PREFIX = "course:comment:";
    /**
     * 评论缓存入库锁
     */
    public static final String COURSE_COMMENT_FLUSH_DB_LOCK = "course:comment:flushDb";
    /**
     * 图书评论点赞数，后面加ID
     */
    public static final String COURSE_COMMENT_FAVOR_NUM = "course:comment:favorNum:";
    /**
     * 图书评论回复数，后面加ID
     */
    public static final String COURSE_COMMENT_REPLY_NUM = "course:comment:replyNum:";
    /**
     * 举报锁前缀
     */
    public static final String COURSE_COMPLAINT_LOCK_PREFIX = "course:complaint:";
}
