package com.dreamer.config;

import com.dreamer.handler.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true)
                .maxAge(3600);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TokenInterceptor(stringRedisTemplate))
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/manager/loginWithPwd",
//                        "/manager/sendCode",
//                        "/manager/loginWithPhone",
//                        //端口测试
//                        "/port",
//                        "/hystrix/**",
//                        "/webjars/**",
//                        "/favicon.ico"
//                );
//    }
}
