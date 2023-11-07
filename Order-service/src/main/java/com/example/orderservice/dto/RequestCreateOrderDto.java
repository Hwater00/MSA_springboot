package com.example.orderservice.dto;

import com.example.orderservice.domain.Order;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @Builder
public class RequestCreateOrderDto {

    private String orderId;

    private Long count;

    private LocalDateTime createAt;

    private String userId;

    private String productId;


    public Order toEntity(){
        return Order.builder()
                .orderId(UUID.randomUUID().toString())
                .count(this.count)
                .createAt(LocalDateTime.now())
                .userId(this.userId)
                .productId(this.productId)
                .build();
    }
}
