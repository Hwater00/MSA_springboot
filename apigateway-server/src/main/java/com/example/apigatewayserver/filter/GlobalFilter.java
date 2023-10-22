package com.example.apigatewayserver.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{
            ServerHttpResponse response = exchange.getResponse();
            ServerHttpRequest request = exchange.getRequest();

            // Config 멤버 변수 정보를 이용해서 전역 필터 내부 코드를 작성

            // 1. pre filter
            System.out.println("global filter default message:"+config.getMessage());
            // pre에서 사용할지 말리 yml파일에서 설정하는 Boolean값으로 판단함
            if(config.isPre()){
                System.out.println("global pre filter" +request.getId());
            }

            // 2. post filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if(config.isPost()){
                    System.out.println("global post filter" + response.getStatusCode());
                }

            }));
        };
    }


    @Getter @Setter
    public static class Config{
        // 설정 파일에서 인자값을 넘겨받을 수 있도록 Config 이너클래스에 멤버 변수들을 선언합니다
        private String message;
        private boolean pre;
        private boolean post;

    }
}
