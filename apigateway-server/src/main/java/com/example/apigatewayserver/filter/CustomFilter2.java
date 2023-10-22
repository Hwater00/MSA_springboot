package com.example.apigatewayserver.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter2 extends AbstractGatewayFilterFactory<CustomFilter2.Config> {

    public CustomFilter2(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            System.out.println("Custom pre filter2:" +request.getId());

            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                System.out.println("Custom post filter3: status code:"+response.getStatusCode());
            }));
        };
    }

//    // 인터페이스인 GatewayFilter를 구현한 구현체 OrderedGatewayFilter를 이용한 우선순위 조정
//    @Override
//    public GatewayFilter apply(Config config) {
//       GatewayFilter gatewayFilter = new OrderedGatewayFilter((exchange, chain) -> {
//
//           // 숫자가 작을수록 우선순위가 높다 = 우선순위:100> 1000
//       }, Ordered.LOWEST_PRECEDENCE); // 필터의 우선순위 MAX_Value 가장 낮은 우선순위를 의미
//    return gatewayFilter;
//    }



    public static class Config {

    }


}
