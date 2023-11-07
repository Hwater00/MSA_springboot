package com.example.itemservice.feginClient;

import com.example.itemservice.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// 발신지: item, 목적지: order
// order service쪽으로 요청할 예정입니다.
// name은 유레카 기준 발신지를 작성, path는 게이트웨이기준,
@FeignClient(name = "ORDER-SERVICE", path = "order-service")
public interface ItemtoOrderFeignClient {

    @GetMapping("orders/{productId}/products")
    public List<Order> getOrdersByItemId(@PathVariable String productId);
}
