package com.example.itemservice.config;

import com.example.itemservice.util.CustomFeignException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfiguration {
    @Bean
    public CustomFeignException getCustomFeginException(){
        return new CustomFeignException();
    }
}
