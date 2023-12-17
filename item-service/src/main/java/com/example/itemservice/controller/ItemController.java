package com.example.itemservice.controller;

import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.dto.ResponseOrderByItemDto;
import com.example.itemservice.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("item-service")
public class ItemController {

    private final ItemService itemService;


    // 빈 초기화 확인 - Spring의 의존성 주입을 사용하는 경우 Environment 빈이 올바르게 초기화되었으며 주입 가능하도록 만들었는지 확인하세요.
    // private final로 하여 @RequiredArgsConstructor 사용
    private final Environment env;

    @GetMapping("health-check")
    public String healthCheck(){
        return "item-service service is available";
    }

    // 등록 성공시 201
    @PostMapping("items")
    public ResponseEntity<?> CreateItem(@RequestBody RequestCreateItemDto ItemDto) throws JsonProcessingException {
        // 서비스에 직접적인 처리 로직을 보내지 않고 적절한 요청인 지 확인, 메세지큐로 보내는 역할만 합니다.
        itemService.publishCreateItemMessage(ItemDto);
        // 메세지 큐에서 서비스로 전달하는 로직을 Cunsumer가 직접적으로 합니다.

        return ResponseEntity.ok("메세지 생성 요청 검증 완료");
    }

    // 전체 아이템 조회

    // UUID로 개별 아이템 조회 : productId

    @GetMapping("pro-check")
    public String configProCheck(){
        return env.getProperty("pro.file");
    }

    // Feign Item to Order
    @GetMapping("items/{productId}/orders")
    public ResponseEntity<?> getOrdersByProductId(@PathVariable String productId){
        ResponseOrderByItemDto dto = itemService.findOrderByProduct(productId);
        return ResponseEntity.ok(dto);
    }

    // PathVariable을 이용해서 message를 큐에 적재할 수 있도록 엔드포인트를 직접 설정
    @GetMapping("items/{message}/message")
    public ResponseEntity<?> publishTestMessage(@PathVariable String message){
        itemService.publishTestMessage(message);
        return ResponseEntity.ok().build();
    }

}
