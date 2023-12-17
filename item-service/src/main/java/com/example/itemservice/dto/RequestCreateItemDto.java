package com.example.itemservice.dto;

import com.example.itemservice.domain.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class RequestCreateItemDto {

    @Pattern(regexp = "^[a-zA-Z가-힣0-9]+")
    @NotBlank(message = "상품명은 반드시 입력해줘야 합니다")
    private String productName;

    private Long stock;
    private Long pricePerItem;

    public Item toEntity(){
        return Item.builder()
                .productId(UUID.randomUUID().toString())
                .productName(this.productName)
                .stock(this.stock)
                .pricePerItem(this.pricePerItem)
                .build();
    }




}
