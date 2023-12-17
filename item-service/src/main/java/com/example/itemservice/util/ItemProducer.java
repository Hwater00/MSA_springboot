package com.example.itemservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemProducer {
    // 멤버 변수로 RabbitTemplate을 생성
    private final RabbitTemplate rabbitTemplate;

    public void sendTestMessage(String message){
// 전송한 message가 큐에 적재
// 서버 내에서 어떤 큐로 보낼 지 세팅 진행:  rabbitTemplate.convertAndSend("대시보드에서 생성한 큐 이름", 보낼 페이지)//
        rabbitTemplate.convertAndSend("ITEM_CREATE_QUEUE",message);
    }

    // java object를 String으로
    public void sendCreateItemMessage(String message){
        rabbitTemplate.convertAndSend("ITEM_CREATE_QUEUE",message);
    }

}
