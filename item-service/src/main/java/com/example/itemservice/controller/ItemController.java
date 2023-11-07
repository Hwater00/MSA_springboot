package com.example.itemservice.controller;

import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.dto.ResponseOrderByItemDto;
import com.example.itemservice.service.ItemService;
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
    public ResponseEntity<?> CreateItem(@RequestBody RequestCreateItemDto ItemDto){
        itemService.createItem(ItemDto);

        return new ResponseEntity(HttpStatus.CREATED);
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

}
