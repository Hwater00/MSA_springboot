package com.example.orderservice.service;

import com.example.orderservice.dto.RequestCreateOrderDto;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    public void createOrder(RequestCreateOrderDto orderDto){
        orderRepository.save(orderDto.toEntity());
    }
}
