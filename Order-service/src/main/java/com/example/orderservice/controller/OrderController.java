package com.example.orderservice.controller;

import com.example.orderservice.domain.Order;
import com.example.orderservice.dto.RequestCreateOrderDto;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("order-service")
public class OrderController {

    private final OrderService orderService;


    @GetMapping("health-check")
    public String healthCheck(){
        return "item-service server is available";
    }

    @PostMapping("orders")
    public ResponseEntity<?> createOrder(@RequestBody RequestCreateOrderDto requestCreateOrderDto){
        orderService.createOrder(requestCreateOrderDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("orders/{userId}/users")
    public ResponseEntity<?> getOrderListByUserId(@PathVariable String userId){
         List<Order> orderList = orderService.getListOrder(userId)
                .orElseThrow(()-> new RuntimeException("없는 유저 아이디로 조회하였습니다"))
                .stream().toList();  // .stream().toList()는 Optional에서 유저 아이디로 조회한 주문 목록을 가져와서, 해당 주문 목록을 리스트로 변환하는 과정을 나타냅니다.
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("orders/{productId}/products")
    public ResponseEntity<?>  getOrderListByProductId(@PathVariable String productId){
        List<Order> orderList = orderService.getOrderListByProductId(productId)
                .orElseThrow(()-> new RuntimeException("없는 아이템 아이디로 조회하셨습니다"))
                .stream().toList();
        return ResponseEntity.ok(orderList);
    }
}
