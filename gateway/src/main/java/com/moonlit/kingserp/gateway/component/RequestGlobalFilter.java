package com.moonlit.kingserp.gateway.component;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @Description: 全局过滤器
 * @Author: Joshua
 * @CreateDate: 2020-03-20 18:08
 * @Version 1.0
 */
@Slf4j
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //顯示請求
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().toString();
        System.out.println("----------------------------------------------------------------------");
        System.out.println(" Request URL: " + uri);
        System.out.println("----------------------------------------------------------------------");

        return chain.filter(exchange.mutate().response(new ServerHttpResponseDecorator(exchange.getResponse()) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
                if (getStatusCode().equals(HttpStatus.OK) && body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = Flux.first(body);
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        System.out.println(dataBuffer.readableByteCount());

                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        //responseData就是下游系统返回的内容,可以查看修改
                        String responseData = new String(content, Charset.forName("UTF-8"));

                        log.debug("响应内容:{}", responseData);
                        log.debug("this is response encrypt");
                        System.out.println(responseData);

                        byte[] newContent = responseData.getBytes();
                        byte[] uppedContent = new String(newContent, Charset.forName("UTF-8")).getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                } else {
                    log.error("响应code异常:{}", getStatusCode());
                }
                return super.writeWith(body);
            }
        }).build());
    }

    @Override
    public int getOrder() {
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
    }
}
