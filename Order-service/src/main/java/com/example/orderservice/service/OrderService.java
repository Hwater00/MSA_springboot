package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.dto.RequestCreateOrderDto;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    public void createOrder(RequestCreateOrderDto orderDto){
        orderRepository.save(orderDto.toEntity());
    }

    // userId를 입력하면 Order리스크를 넘겨주는 메서드 호출을 통해 리턴
    public Optional<List<Order>> getListOrder(String userId){
        Optional<List<Order>> orderUserList = orderRepository.findOrderByUserIdOrderByCreateAtDesc(userId);
//                .orElseThrow(()->new RuntimeException( "해당 유저가 없습니다"));
        return orderUserList;
    }

    public Optional<List<Order>> getOrderListByProductId(String productId){
        return orderRepository.findOrderByProductId(productId);

    }

}
