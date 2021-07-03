package com.xiao.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 用于模拟认证的过滤器
 *
 * @author xiao ji hao
 * @create 2021年07月03日 15:20:00
 */
@Component
public class AuthorFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //模拟获取token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        //模拟获取用户
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if(token == null) {
            //认证不通过
            exchange.getResponse().setStatusCode(HttpStatus.MULTI_STATUS);
            return exchange.getResponse().setComplete();
        }
        //通过
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //过滤器执行的顺序，0表示第一个执行
        return 0;
    }
}
