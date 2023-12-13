package com.example.dblock.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponCountRepository {

    private final RedisTemplate<String,String> redisTemplate;

    public Long increment(){
        return redisTemplate.opsForValue().increment("couponcount");
    }

    public void reset(){
        redisTemplate.opsForValue().set("couponcount","0");
        // commit 되기 전에 아토믹 연산을 빠르게 진행함으로 요청을 누락하지 않을 수 있음
    }
}
