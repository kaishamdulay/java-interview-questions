package com.collective.powerplant.config;

import com.collective.powerplant.interceptors.ApiKeyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    private final ApiKeyInterceptor apiKeyInterceptor;


    @Autowired
    public WebMvcConfig(
            ApiKeyInterceptor apiKeyInterceptor) {
        this.apiKeyInterceptor = apiKeyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final String rootPattern = "/v1/**";

        registry.addInterceptor(apiKeyInterceptor).addPathPatterns(rootPattern);
    }
}