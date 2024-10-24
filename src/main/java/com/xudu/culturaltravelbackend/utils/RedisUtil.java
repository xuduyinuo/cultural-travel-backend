package com.xudu.culturaltravelbackend.utils;

import cn.hutool.core.lang.Assert;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存信息
     */
    public void setRedisContent(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 读取缓存信息
     */
    public String getRedisContent(String key) {
        return redisTemplate.opsForValue().get(key).toString();
    }

    /**
     * 更新缓存信息
     */
    public Boolean updateRedisContent(String key, String value) {
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除缓存信息
     */
    public Boolean deleteRedisContent(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置缓存失效时间
     */
    public Boolean setRedisExpire(String key, Long expireTime) {
        try {
            if (expireTime > 0) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取缓存失效时间
     */
    public Long getRedisExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 查询key是否存在
     */
    public Boolean isRedisExist(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入缓存信息（包含失效时间）
     */
    public void setRedisContent(String key, String value, Long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 写入缓存信息
     */
    public void setRedisContent(String key, Object object, long expireTime, TimeUnit timeUnit) {
        Assert.notNull(timeUnit, "过期时间单位不能为空");
        redisTemplate.opsForValue().set(key, object, expireTime, timeUnit);
    }
}