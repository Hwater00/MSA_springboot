package com.example.orderservice.repository;

import com.example.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    // feign을 위한 쿼리메서드로 userId를 이용해 해당 유저의 전체 order 목록을 가져오는 메서드
    Optional<List<Order>> findOrderByUserIdOrderByCreateAtDesc (String userId);

    // productId는 ItemId와 같습니다.
    Optional<List<Order>> findOrderByProductId(String productId);

}
