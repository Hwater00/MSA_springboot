package com.example.userservice.feignclient;

import com.example.userservice.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// name: 유레카에 등록된 서비스명, path:게이트웨이 서버에서 붙는 서비스 앞단에 붙는 접두어
@FeignClient(name="ORDER-SERVICE",path = "order-service") // order-service로 유레카에 등록된 서비스의
public interface UsertoOrderFeignClient {

    @GetMapping("/orders/{userId}/users") // orders/{userId}로 호출을 넣으면
    public List<Order> getOrderListByUserId(@PathVariable String userId); // 요청을 처리한다


}
