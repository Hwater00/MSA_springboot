package com.example.dblock.service;

import com.example.dblock.repository.CouponRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class ConfirmServiceTest {

    @Autowired
    private ConfirmService confirmService;

    @Autowired
    private CouponRepository couponRepository;

    @AfterEach
    public void dbreset(){
        couponRepository.deleteAll(); // 모든 로직이 끝나면 DB 초기화
        confirmService.reset();
    }

    @Test
    @DisplayName("쿠폰을 하나 발급해서 DB에 하나의 내역이 저장되는지 확인한다.")
    public void 쿠폰하나만발급하기(){
        // given

        // when
        confirmService.confirm(1L);
        // then
        assertEquals(1,couponRepository.count());
    }

// 두 테스트 사이에 reset로직이 필요함

    @Test
    @DisplayName("쿠폰발급은 멀티쓰레드 형식으로 100개의 요청을 넣고, 100개까지만 발급되는지 확인")
    public void 쿠폰1000개요청100개만들기() throws InterruptedException{
        // 동시 요청의 개수
        final int threadCount = 100;

        //32 스레드가 1000개를 동시에 요청넣도록 설정
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 요청을 마친 쓰레드는 전체 쓰레드 풀이 끝날때까지 대기하도록 처리
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0; i< threadCount; i++){
            long userId = i;
            //
            executorService.submit(()->{
                try {
                    confirmService.confirm(userId);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        // 모든 쓰레드의 호출이 끝나면 쓰레드 풀 자체를 종료
        countDownLatch.await();

        assertEquals(100, couponRepository.count());
    }


}
