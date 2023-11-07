package com.example.userservice.domain;

// ResponseFindUserDto에서  import 에러를 막기 위해서 생성

import lombok.*;

import java.time.LocalDateTime;

// Entity 목적으로 만들지 않음
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Order {
    private Long id;
    private String orderId;
    private Long count;
    private LocalDateTime createAt;
    private String userId;
    private String productId;
}
