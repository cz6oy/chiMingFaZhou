package com.baizhi.cache;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;

@Configuration
@Aspect
public class RedisCache {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StringBuilder sb = new StringBuilder();
        //获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        sb.append(methodName);

        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        for(Object arg:args){
            sb.append(arg);
        }

        String str = sb.toString();
        Boolean aBoolean = redisTemplate.hasKey(className);
        HashOperations hashOperations = redisTemplate.opsForHash();
        Object  result = null;
        if(aBoolean){
            result = hashOperations.get(className,str);
        }else{
            result = proceedingJoinPoint.proceed();
            HashMap<Object, Object> map = new HashMap<>();
            map.put(str,result);
            hashOperations.putAll(className,map);
        }
        return result;
    }

    @After("@annotation(com.baizhi.annotation.DelCache)")
    public void arter(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println("----------------"+className);
        redisTemplate.delete(className);
    }
}
