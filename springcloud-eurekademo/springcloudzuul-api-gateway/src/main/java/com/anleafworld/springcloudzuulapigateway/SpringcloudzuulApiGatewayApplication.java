package com.anleafworld.springcloudzuulapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class SpringcloudzuulApiGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringcloudzuulApiGatewayApplication.class).web(true).run(args);
    }

}

