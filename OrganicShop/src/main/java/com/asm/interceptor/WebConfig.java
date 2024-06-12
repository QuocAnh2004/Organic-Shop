//package com.asm.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.asm.interceptor.AuthorizationInterceptor;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private AuthorizationInterceptor authorizationInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorizationInterceptor)
//                .addPathPatterns("/app/admin/**","/app/user/checkout/**")  // Đường dẫn cần áp dụng Interceptor
//                .excludePathPatterns("/public/**", "/app/user/login", "/app/user/register"); // Đường dẫn loại trừ
//    }
//}
package com.asm.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/app/admin/**", "/app/user/checkout/**", "/app/user/editProfile/")  // Đường dẫn cần áp dụng Interceptor
                .excludePathPatterns("/public/**", "/app/user/login", "/app/user/register"); // Đường dẫn loại trừ
    }
}
