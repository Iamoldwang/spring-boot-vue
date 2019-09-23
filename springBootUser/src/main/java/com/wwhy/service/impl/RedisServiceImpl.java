package com.wwhy.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.wwhy.service.RedisService;

/**
 * key定义规则：object-type:id:field  user:1000:password
 *
 * @author wangpan
 * create date:2018年2月7日
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {
    private RedisTemplate<Serializable, Object> redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Integer expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

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
    public boolean expire(final String key, Integer expireTime) {
        boolean result = false;
        try {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @param user
     * @return
     * @description:获取用户购物车
     * @time:2018年3月15日 下午1:50:59
     * @user:WangPan
     */
    public List<Object> getAllCart(String key) {
        List<Object> carts = redisTemplate.opsForList().range(key, 0, -1);
        return carts;
    }

    /**
     * @param key
     * @param index
     * @param value
     * @return
     * @description:修改购物车中的信息
     * @time:2018年3月15日 下午2:18:49
     * @user:WangPan
     */
    public boolean updateList(String key, long index, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForList().set(key, index, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key
     * @param count
     * @param value
     * @return
     * @description:删除购物车中的信息
     * @time:2018年3月15日 下午2:18:59
     * @user:WangPan
     */
    public boolean removeList(String key, long count, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForList().remove(key, count, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
