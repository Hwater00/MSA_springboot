package com.example.itemservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="item")
@Builder
@Getter @AllArgsConstructor @NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //아이템 번호

    private String productId; // 아이템 고유 식별 uuid

    private String productName; //판매처에서 사용하는 상품명

    private Long stock; //재고량

    private Long pricePerItem;//개당 가격



}
