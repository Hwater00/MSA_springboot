package com.example.itemservice.util;

import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemConsumer {

    private final ObjectMapper objectMapper;
    private final ItemService itemService;

    // 어노테이션이 큐에 담긴 것을 인식합니다. 단 내부 로직을 실행하고 다음을 수행한 준비가 될 때만 동작함
    @RabbitListener(queues = "ITEM_CREATE_QUEUE")
    public void getTestMessage(String message){
        System.out.println("큐에서 뽑아낸 메세지:"+ message);
    }

    // json-> 엔티티
    @RabbitListener(queues = "ITEM_CREATE_QUEUE")
    public void createItem(String message) throws JsonProcessingException { // 큐에서 뽑아낸 message
        // objectMapper.readValue("String형식인 JSON", 목적객체.class == DTO.class);
        RequestCreateItemDto dto = objectMapper.readValue(message, RequestCreateItemDto.class);
        // service단에서 DTO를 입력받아 DB에 INSERT해주는 로직을 호출
        itemService.createItem(dto);
    }
}
