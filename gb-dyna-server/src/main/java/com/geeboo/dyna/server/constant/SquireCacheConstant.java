package com.geeboo.dyna.server.constant;

/**
 * Title: <br>
 * Description: Copyright: Copyright (c) 2018
 *
 * @author 郭明毅 guomy 创建时间:2018/9/28 20:06
 */
public class SquireCacheConstant {
    /**
     * 业务类型：笔记
     */
    public static final int BUSINESS_TYPE_NOTE = 1;
    /**
     * 业务类型：图书
     */
    public static final int BUSINESS_TYPE_BOOK = 2;
    /**
     * 业务类型：读后感
     */
    public static final int BUSINESS_TYPE_READ_BOOK = 3;
    /**
     * 业务类型：分享书内容
     */
    public static final int BUSINESS_TYPE_SHARE_BOOK = 4;
    /**
     * 业务类型：图文，已弃用入口，只做历史数据展示-20181022
     */
    public static final int BUSINESS_TYPE_PICTURE = 5;
    /**
     * 业务类型：档案
     */
    public static final int BUSINESS_TYPE_RECORD = 6;

    /**
     * 广场列表缓存KEY
     */
    public static final String SQUIRE_LIST_KEY = "squire:list";
    /**
     * 大于此值，则进行<code>SQUIRE_LIST_KEY</code>的长度截取
     */
    public static final double CEIL_TO_TRIM_LIST = 0.8d;
    /**
     * <code>SQUIRE_LIST_KEY</code>的长度截取分布式锁
     */
    public static final String CEIL_TO_TRIM_LOCK = "squire:list:trim";
    /**
     * 广场列表长度
     */
    public static final long SQUIRE_LIST_LENGTH = 100L;
    /**
     * 动态内容前缀，后面加动态ID
     */
    public static final String SQUIRE_OBJECT_PREFIX = "squire:object:";
    /**
     * 用户动态数量统计前缀，后面加用户ID
     */
    public static final String USER_SQUIRE_NUM_PREFIX = "squire:user:num:";
    /**
     * 动态相关统计初始化锁
     */
    public static final String SQUIRE_INIT_LOCK = "squire:init";
    /**
     * 点赞锁
     */
    public static final String SQUIRE_FAVOR_LOCK_PREFIX = "squire:favor:";
    /**
     * 动态点赞数量统计前缀，后面加广场ID
     */
    public static final String SQUIRE_FAVOR_NUM_PREFIX = "squire:favor:num:";
    /**
     * 评论锁
     */
    public static final String SQUIRE_COMMENT_LOCK_PREFIX = "squire:comment:";
    /**
     * 动态评论数量统计前缀，后面加广场ID
     */
    public static final String SQUIRE_COMMENT_NUM_PREFIX = "squire:comment:num:";
    /**
     * 动态统计缓存入库锁
     */
    public static final String SQUIRE_STAT_FLUSH_LOCK = "squire:stat:flush:";
    /**
     * 动态用户统计缓存入库锁
     */
    public static final String SQUIRE_USER_STAT_FLUSH_LOCK = "squire:user:stat:flush:";
}
