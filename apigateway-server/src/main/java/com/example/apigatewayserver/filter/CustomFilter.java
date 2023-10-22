package com.example.apigatewayserver.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

// reactive 비동기 처리와 관련된 패키지로 import하기
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;


@Component // Bean 등록
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {// <>자리는 설정 클래스가 들어가는 자리-> CustomFilter 내부 클래스인 Config를 가리킴

    public CustomFilter(){
        super(Config.class); // 생성자를 이용해 내부클래스 내역을 super()로 보냄
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> { // exchange과 chain을 람디식으로 받는다.
            // 대부분의 필터의 경우 요청이 들어왔을 때 사전에 필터힝 - 내장 객체 필요
            // 응답이 진행될 때 사후에 작업을 진행 - 내장 객체 필요
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // pre filter : 그냥 작성해도 작동함
            System.out.println("Custom pre filter:" +request.getId());

            // post filter : return 구문 안에 코드로 작성해야 작동함
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                System.out.println("Custom post filter:"+response.getStatusCode());
            }));

        };
    }

    public static class Config{ // CustomFilter의 내부클래스인 Config를 생성

    }
}
