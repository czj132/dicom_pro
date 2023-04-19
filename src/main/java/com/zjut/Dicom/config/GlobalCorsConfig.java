package com.zjut.Dicom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {

    // 当前跨域请求最大有效时长。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean
    public CorsFilter corsFilter() {
        //1. 添加 CORS配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //放行哪些原始域
        corsConfiguration.addAllowedOrigin("*");
        //是否发送 Cookie
        corsConfiguration.setAllowCredentials(false);
        //放行哪些请求方式
        corsConfiguration.addAllowedMethod("*");
        //放行哪些原始请求头部信息
        corsConfiguration.addAllowedHeader("*");
        //暴露哪些头部信息
        corsConfiguration.addExposedHeader("*");
        corsConfiguration.setMaxAge(MAX_AGE);
        //2. 添加映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //对接口配置跨域设置
        source.registerCorsConfiguration("/**",corsConfiguration);
        //3. 返回新的CorsFilter
        return new CorsFilter(source);
    }
}
