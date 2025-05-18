package com.example.thangcachep.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigCors implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**") // Áp dụng cho tất cả các đường dẫn
              .allowedOrigins("*") // Cho phép tất cả các domain
              .allowedMethods("GET", "POST", "PUT", "DELETE") // Các phương thức HTTP cho phép
              .allowedHeaders("*"); // Các header cho phép
  }
}




