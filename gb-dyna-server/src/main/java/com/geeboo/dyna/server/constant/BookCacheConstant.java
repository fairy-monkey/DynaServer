package com.geeboo.dyna.server.constant;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/21 14:22
 */
public class BookCacheConstant {
    /**
     * 图书评论数，后面加ID
     */
    public static final String BOOK_COMMENT_NUM = "book:commentNum:";
    /**
     * 图书评论评论锁，后面加ID
     */
    public static final String BOOK_COMMENT_LOCK_PREFIX = "book:comment:";
    /**
     * 评论缓存入库锁
     */
    public static final String BOOK_COMMENT_FLUSH_DB_LOCK = "book:comment:flushDb";
    /**
     * 图书评论点赞数，后面加ID
     */
    public static final String BOOK_COMMENT_FAVOR_NUM = "book:comment:favorNum:";
    /**
     * 图书评论回复数，后面加ID
     */
    public static final String BOOK_COMMENT_REPLY_NUM = "book:comment:replyNum:";
    /**
     * 举报锁前缀
     */
    public static final String BOOK_COMPLAINT_LOCK_PREFIX = "book:complaint:";




}
