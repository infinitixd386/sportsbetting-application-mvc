package com.epam.training.sportsbetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig {

    @Bean
    public InternalResourceViewResolver viewResolver(){
        return new InternalResourceViewResolver();
    }

    @Bean
    public SimpleMappingExceptionResolver exceptionResolver(){
        return new SimpleMappingExceptionResolver();
    }
}
