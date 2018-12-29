package com.anleafworld.springcloudzuulapigateway;

import com.anleafworld.springcloudzuulapigateway.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class SpringcloudzuulApiGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringcloudzuulApiGatewayApplication.class).web(true).run(args);
    }

    //编辑完AccessFilter后并不会生效，需写增加如下bean
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

}

