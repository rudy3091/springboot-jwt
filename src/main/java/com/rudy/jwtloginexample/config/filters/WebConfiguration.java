package com.rudy.jwtloginexample.config.filters;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("OPTION", "POST", "GET")
                .allowedOrigins("http://localhost:3000")
                .allowCredentials(true);
    }
}

