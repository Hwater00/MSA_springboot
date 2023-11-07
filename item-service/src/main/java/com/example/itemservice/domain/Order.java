package com.example.itemservice.domain;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

// DTO보다는 VO로 만들기
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString @Builder
public class Order {
    private Long id;

    @Column(updatable = false) // 주문 코드 불변
    private String orderId;

    private Long count;

    @CreatedDate
    @Column(updatable = false) // 최초 입력 값 유지, 가입 시간은 불변
    private LocalDateTime createAt;

    private String userId;

    private String productId;
}
