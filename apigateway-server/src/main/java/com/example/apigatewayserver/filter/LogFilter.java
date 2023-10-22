package com.example.apigatewayserver.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {

    public LogFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("log filter: {}", request.getId());

            return chain.filter(exchange).then(Mono.fromRunnable(()->{ //Mono 는 스프링 5에서 추가되었는데 비동기방식 서버에서 단일값 전달할 때 사용하는 양식입니다.
                log.info("log post filter {}",response.getStatusCode());
            }));

        };
    }


    public static class Config{

    }
}
