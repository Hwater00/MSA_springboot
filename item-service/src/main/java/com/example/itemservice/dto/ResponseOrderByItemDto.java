package com.example.itemservice.dto;

import com.example.itemservice.domain.Item;
import com.example.itemservice.domain.Order;
import lombok.*;

import java.util.List;

// 도메인 Item과 같은 멤버 필드 값에 List<Order>만 추가됨 , @Setter 꼭 필요함
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ResponseOrderByItemDto {
    private Long id;

    private String productId; // 아이템 고유 식별 uuid

    private String productName; //판매처에서 사용하는 상품명

    private Long stock; //재고량

    private Long pricePerItem;//개당 가격

    private List<Order> orderList;

    public static ResponseOrderByItemDto FromDto(Item item){
        return ResponseOrderByItemDto.builder()
                .productId(item.getProductId())
                .productName(item.getProductName())
                .stock(item.getStock())
                .pricePerItem(item.getPricePerItem())
                .build();
    }
}
