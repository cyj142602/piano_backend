package com.cyj.piano_backend.bean;

import com.cyj.piano_backend.redis.RedisBaseDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author changyingjie
 */

public class Test {
//    public static void main(String[] args) {
//        System.out.println(IdUtil.fastSimpleUUID());
//        System.out.println(IdUtil.fastUUID());
//        System.out.println(IdUtil.randomUUID());
//        System.out.println(IdUtil.simpleUUID());
//
//    }

    @Resource
    private RedisBaseDao redisBaseDao;

    @Scheduled(cron = "0/15 * * * * ?")
    public void test(){
        System.out.println("dsaasdas");
        redisBaseDao.set("abc","1");
    }
}
