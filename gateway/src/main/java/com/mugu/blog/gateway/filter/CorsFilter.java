//package com.mugu.blog.gateway.filter;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsUtils;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
///**
// * 跨域的配置
// */
//@Component
//public class CorsFilter implements WebFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        //1.配置跨域
//        //允许哪种请求头跨域
//        corsConfiguration.addAllowedHeader("*");
//        //允许哪种方法类型跨域 get post delete put
//        corsConfiguration.addAllowedMethod("*");
//        // 允许哪些请求源跨域
//        corsConfiguration.addAllowedOrigin("*");
//        // 是否携带cookie跨域
//        corsConfiguration.setAllowCredentials(true);
//
//        //允许跨域的路径
//        source.registerCorsConfiguration("/**",corsConfiguration);
//        return new CorsWebFilter(source);
//    }
//}
