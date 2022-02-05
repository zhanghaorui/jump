package com.zhang.redis.constant;

/**
 * redis key 常量
 *
 * @author zhonghuashishan
 */
public class RedisKeyConstants {

    /**
     * 用户发布的菜谱key前缀
     */
    public static final String USER_COOKBOOK_PREFIX = "user_cookbook:";

    /**
     * 用户发布的菜谱key前缀
     */
    public static final String USER_COOKBOOK_COUNT_PREFIX = "user_cookbook:count:";

    /**
     * 用户发布的菜谱key前缀
     */
    public static final String USER_COOKBOOK_PAGE_PREFIX = "user_cookbook:page:";

    /**
     * 菜谱信息key前缀
     */
    public static final String COOKBOOK_PREFIX = "cookbook_info:";

    /**
     * 作者信息前缀
     */
    public static final String USER_INFO_PREFIX = "user_info:";

    /**
     * 商品信息前缀
     */
    public static final String GOODS_INFO_PREFIX = "goods_info:";

    /**
     * 菜谱信息修改锁前缀
     */
    public static final String COOKBOOK_UPDATE_LOCK_PREFIX = "cookbook_update_lock:";

    /**
     * 作者信息修改锁前缀
     */
    public static final String USER_UPDATE_LOCK_PREFIX = "user_update_lock:";

    /**
     * 菜谱信息锁前缀
     */
    public static final String COOKBOOK_LOCK_PREFIX = "cookbook_info_lock:";

    /**
     * 用户发布的菜谱锁前缀
     */
    public static final String USER_COOKBOOK_LOCK_PREFIX = "user_cookbook_lock:";

    /**
     * 作者信息锁前缀
     */
    public static final String USER_LOCK_PREFIX = "user_info_lock:";

    /**
     * 商品信息锁前缀
     */
    public static final String GOODS_LOCK_PREFIX = "goods_info_lock:";
}
