package com.example.apigatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator gatewayLocator(RouteLocatorBuilder builder){ //이미 구현되어있는 RouteLocator 같은 경우에는 위쪽의 어노테이션을 클래스쪽에 붙이기 어렵기 때문에 메소드 위에 @Bean을 등록함
    // -> 만든 클래스가 아닌 것을 빈 컨테이너에 등록하기 위함
    return builder.routes() // 라우팅 정보 시작 지점
            // 개별 라우팅 정보를 등록하는 route() 매서드
            .route(r->
                // 아래와 같이 단일값 return인 경우 return 및  { } 생략 가능
                r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("fsreqh", "fshreqv") //필터 적용으로  자유롭게 설정한 헤더 명칭, 헤더 값을 받는다.
                                .addResponseHeader("fsres", "fsreshv"))
                        .uri("http://localhost:8001/")
            )
            .build(); // 빌더 패턴 종료 = 라우팅 정보 등록 종료 지점
    }
}
