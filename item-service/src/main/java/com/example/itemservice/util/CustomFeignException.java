package com.example.itemservice.util;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomFeignException implements ErrorDecoder {

    // ErrorDecoder 인터페이스의 메서드를 오버라이드하고 구현=> 에러가 다른 프로그램(게이트웨이)으로 전달되도록 구현
    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 404:
                if(methodKey.contains("getListOrder")){
                    return new RuntimeException("해당하는 지원이 존재하지 않습니다");
                }

        }
        return new RuntimeException("분류되지 않은 에러입니다.");
    }
}
