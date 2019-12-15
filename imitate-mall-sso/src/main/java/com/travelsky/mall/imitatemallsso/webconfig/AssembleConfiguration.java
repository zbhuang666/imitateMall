package com.travelsky.mall.imitatemallsso.webconfig;

import com.travelsky.mall.imitatemallsso.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Component
@Configuration
@ComponentScan(basePackages = "com.travelsky.mall.imitatemallsso")
public class AssembleConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public AuthorizationInterceptor authorization() {
        return new AuthorizationInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
