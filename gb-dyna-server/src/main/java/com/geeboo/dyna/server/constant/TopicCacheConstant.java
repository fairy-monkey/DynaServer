package com.geeboo.dyna.server.constant;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/6 17:25
 */
public class TopicCacheConstant {

    /**
     * 话题锁，后面加ID
     */
    public static final String TOPIC_LOCK_PREFIX = "topic:";

    /**
     * 话题缓存入库锁
     */
    public static final String TOPIC_FLUSH_DB_LOCK = "topic:flushDb";
    /**
     * 话题全部排序锁
     */
    public static final String TOPIC_SORT_ALL_LOCK = "topic:sort";

    /**
     * 话题正文，后面需要拼接ID
     * 正文分开缓存，避免分片容量使用不均
     */
    public static final String TOPIC_CONTENT_PREFIX = "topic:content:";

    /**
     * 话题排序zset
     */
    public static final String TOPIC_SORT_ZSET = "topic:sort";

    /**
     * 话题排序UUID，用于标识当前排序与客户端缓存是否一致
     */
    public static final String TOPIC_SORT_UUID = "topic:sortUUID";
    /**
     * 话题最近参与用户，根据当前时间倒序，最多3个
     */
    public static final String TOPIC_RECENT_ACCOUNT = "topic:recent:account:";
    /**
     * 书大痴的评论
     */
    public static final String TOPIC_COMMENT_IDIOT = "topic:comment:idiot:";

    /**
     * 用户最新评论
     */
    public static final String TOPIC_COMMENT_USER = "topic:comment:user:";
    /**
     * 话题浏览数
     */
    public static final String TOPIC_VIEW_NUM = "topic:viewNum:";
    /**
     * 话题评论数
     */
    public static final String TOPIC_COMMENT_NUM = "topic:commentNum:";
    /**
     * 话题参与讨论数
     */
    public static final String TOPIC_PARTICIPATE_NUM = "topic:participateNum:";
    /**
     * 话题回复数
     */
    public static final String TOPIC_REPLY_NUM = "topic:replyNum:";

    /**
     * 话题评论锁，后面加ID
     */
    public static final String TOPIC_COMMENT_LOCK_PREFIX = "topic:comment:";

    /**
     * 评论缓存入库锁
     */
    public static final String TOPIC_COMMENT_FLUSH_DB_LOCK = "topic:comment:flushDb";

    /**
     * 话题评论点赞数，后面加ID
     */
    public static final String TOPIC_COMMENT_FAVOR_NUM = "topic:comment:favorNum:";

    /**
     * 话题评论点赞数，后面加ID
     */
    public static final String TOPIC_COMMENT_REPLY_NUM = "topic:comment:replyNum:";
    /**
     * 举报锁前缀
     */
    public static final String TOPIC_COMPLAINT_LOCK_PREFIX = "topic:complaint:";
}
