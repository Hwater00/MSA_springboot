package com.example.itemservice.controller;

import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.dto.ResponseBuyItemDto;
import com.example.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("item-service")
public class ItemController {

    private final ItemService itemService;

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

    // UUID로 개별 아이템 조회
}
