package com.example.dblock.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class LettuceRepository {

    // 레디스 요소는 키도 String, 벨류도 String
    private final RedisTemplate<String,String> redisTemplate;

    public Boolean lock(Long key){
        // 레디스 내부에 키 값이 존재하지 않으면 새로 만들어주는 명령어 -> 한 개의 스레드만 락을 생성하도록 제한함.
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateKey(key),"lock", Duration.ofMillis(3_000));

    }

    public void checkRedis(){
        redisTemplate.opsForValue().setIfAbsent("test", "test", Duration.ofMillis(1_000));
        System.out.println(redisTemplate.opsForValue().get("test"));
        redisTemplate.delete("test");
    }

    private String generateKey(Long key) {
        // 레디스 기본 키 설정은 Long임으로 Long에서 String으로 변경
        return key.toString();
    }

    public Boolean unlock(Long key){
        // 특정 키 값을 지우기
        return redisTemplate.delete(generateKey(key));
    }
}
