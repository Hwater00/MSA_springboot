package com.example.itemservice.service;

import com.example.itemservice.domain.Item;
import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepoaitory;

    public void createItem(RequestCreateItemDto ItemDto){
        Item saveItem = ItemDto.toEntity();
        itemRepoaitory.save(saveItem);
    }
}
