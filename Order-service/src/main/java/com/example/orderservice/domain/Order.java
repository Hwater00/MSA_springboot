package com.example.orderservice.domain;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false) // 주문 코드 불변
    private String orderId;

    private Long count;

    @CreatedDate
    @Column(updatable = false) // 최초 입력 값 유지, 가입시간은 불변
    private LocalDateTime createAt;

    private String userId;

    private String productId;

}
