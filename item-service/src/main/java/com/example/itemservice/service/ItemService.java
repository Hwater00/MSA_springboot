package com.example.itemservice.service;

import com.example.itemservice.domain.Item;
import com.example.itemservice.domain.Order;
import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.dto.ResponseOrderByItemDto;
import com.example.itemservice.feginClient.ItemtoOrderFeignClient;
import com.example.itemservice.repository.ItemRepository;
import com.example.itemservice.util.CustomFeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemtoOrderFeignClient orderFeignClient;

    public void createItem(RequestCreateItemDto ItemDto){
        Item saveItem = ItemDto.toEntity();
        itemRepository.save(saveItem);
    }

    public ResponseOrderByItemDto findOrderByProduct(String productId){
        // 1. 요청측 -> 특정 아이템을 가져옵니다
        Item finditem = itemRepository.findItemByProductId(productId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이템은 없습니다"));

        // 2. 요청하는쪽+요청받는쪽DTO-> ResponseOrderByItemDTO로 변경하는 코드 추가
        ResponseOrderByItemDto itemDto = ResponseOrderByItemDto.FromDto(finditem);

        // 3. 요청받는쪽으로 FEGIN사용하여 API요청-> feign 클라이언트를 이용해서 특정 아이템의 구매목록을 가져옵니다.
        List<Order> orderList = orderFeignClient.getOrdersByItemId(productId);

        // 4. 요청받는쪽 값을 요청하는 쪽의 필드로 합침-> ResponseOdrerByItemDTO내 setter를 통해 합치는 과정 진행
        itemDto.setOrderList(orderList);

        // 5. 합쳐준 DTO(ResponseOrderByItemDto)를 리턴
        return itemDto;
    }
}
