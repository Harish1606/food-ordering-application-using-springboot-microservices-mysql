package com.ford.foa_cloud_gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/authenticationFallback")
    public Mono<String> authenticationServiceFallback(){
        return Mono.just("Authentication service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/orderFallback")
    public Mono<String> orderServiceFallback(){
        return Mono.just("Order service is taking too long to respond or is down. Please try again later");
    }

}
