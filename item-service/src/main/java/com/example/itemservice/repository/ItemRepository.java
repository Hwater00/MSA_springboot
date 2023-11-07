package com.example.itemservice.repository;

import com.example.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {
    public Optional<Item> findItemByProductId(String productId);

}
