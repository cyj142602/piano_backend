package com.cyj.piano_backend.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @project  cloudplatform
 * @package com.css.ztb.cxpt.redis
 * @file RedisBaseDao.java 创建时间:2019年4月17日下午3:55:33
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2019 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 * @module 模块: 模块名称
 * @author   史雪涛
 * @reviewer 审核人
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Service
public class RedisBaseDao {

    /**
     * redisTemplate
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 删除缓存<br>
     * 根据key精确匹配删除
     * 
     * @param key key
     */
    @SuppressWarnings("unchecked")
    public void deleteKey(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 取得缓存（字符串类型）
     * 
     * @param key key
     * @return String
     */
    public String getString(String key) {
        final String value = (String) redisTemplate.boundValueOps(key).get();
        return value;
    }

    /**
     * 将value对象写入缓存
     * 
     * @param key key
     * @param value value
     */
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将value对象写入缓存
     * 
     * @param key key
     * @param value value
     *            失效时间(秒)
     */
    public void set(String key, Object value) {
        if (value.getClass().equals(String.class)) {
            redisTemplate.opsForValue().set(key, value.toString());
        }

    }

    /**
     * 指定缓存的失效时间
     * 
     * @author FangJun
     * @date 2016年8月14日
     * @param key key
     *            缓存KEY
     * @param timeout timeout
     *            失效时间(秒)
     */
    public void expire(String key, long timeout) {
        if (timeout > 0) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.boundValueOps(key).getExpire();
    }

    /**
     * 模糊查询keys
     * 
     * @param pattern pattern
     * @return set
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 计数器
     * 
     * @param key key
     */
    public void increment(String key) {
        redisTemplate.opsForValue().increment(key, 1);
    }

    public void decrement(String key) {
        redisTemplate.opsForValue().decrement(key,1);
    }

    /**
     * 可设置步长计数器
     * @param key
     * @param step
     */
    public Long increment(String key, Long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * 首次设置返回true
     * @param key
     * @param value
     * @return
     */
    public Boolean setIfAbsent(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 批量设置hash键值对
     * @param key
     * @param dataMap
     */
    public void putAll(String key, Map<Object, Object> dataMap) {
        redisTemplate.boundHashOps(key).putAll(dataMap);
    }

    /**
     * 设置hash键值对
     * @param key
     * @param hkey
     * @param hval
     */
    public void put(String key, Object hkey, Object hval) {
        redisTemplate.boundHashOps(key).put(hkey, hval);
    }

    /**
     * 获取单个hash键值对的值
     * @param key
     * @param hkey
     * @return
     */
    public Object get(String key, Object hkey) {
        return redisTemplate.boundHashOps(key).get(hkey);
    }

    /**
     * 批量获取hash键值对得值
     * @param key
     * @param keys
     * @return
     */
    public List<Object> multiGet(String key, List<String> keys) {
        return redisTemplate.boundHashOps(key).multiGet(Collections.singleton(keys));
    }

    /**
     * 获取hash单个键值对
     * @param key
     * @return
     */
    public Map<Object, Object> entries(String key) {
        return redisTemplate.boundHashOps(key).entries();
    }
    
}