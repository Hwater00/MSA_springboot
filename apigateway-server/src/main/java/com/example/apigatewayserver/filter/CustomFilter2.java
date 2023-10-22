package com.example.apigatewayserver.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
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



    public static class Config {

    }


}
