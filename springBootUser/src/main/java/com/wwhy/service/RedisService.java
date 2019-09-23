package com.wwhy.service;

import java.util.List;



public interface RedisService {
    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys);

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern);

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key);

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key);

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key);

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value);

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Integer expireTime);

    /**
     * 设置失效时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     * @author wangpan
     * create date:2018年2月27日
     */
    public boolean expire(final String key, Integer expireTime);



    /**
     * @param key
     * @return
     * @description:获取购物车
     * @time:2018年3月15日 下午2:18:42
     * @user:WangPan
     */
    public List<Object> getAllCart(String key);

    /**
     * @param key
     * @param index
     * @param value
     * @return
     * @description:修改购物车中的信息
     * @time:2018年3月15日 下午2:18:49
     * @user:WangPan
     */
    public boolean updateList(String key, long index, Object value);

    /**
     * @param key
     * @param count
     * @param value
     * @return
     * @description:删除购物车中的信息
     * @time:2018年3月15日 下午2:18:59
     * @user:WangPan
     */
    public boolean removeList(String key, long count, Object value);
}
