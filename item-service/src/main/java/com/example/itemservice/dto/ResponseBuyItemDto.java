package com.example.itemservice.dto;

import com.example.itemservice.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ResponseBuyItemDto {

    private String productId;

    private String productName;

    Long stock;

    long pricePerItem;

    String userID;

    String orderId;


}
