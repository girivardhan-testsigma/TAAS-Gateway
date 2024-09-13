package org.example.taas_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("saucelabs_route", r -> r.path("/wd/hub/saucelabs/**")
                        .filters(f -> f.rewritePath("/wd/hub/saucelabs/(?<segment>.*)", "/wd/hub/${segment}"))
                        .uri("https://ondemand.us-west-1.saucelabs.com:443"))
                .route("lambdatest_route", r -> r.path("/wd/hub/lambdatest/**")
                        .filters(f -> f.rewritePath("/wd/hub/lambdatest/(?<segment>.*)", "/wd/hub/${segment}"))
                        .uri("https://api.lambdatest.com/wd/hub"))
                .route("browserstack_route", r -> r.path("/wd/hub/browserstack/**")
                        .filters(f -> f.rewritePath("/wd/hub/browserstack/(?<segment>.*)", "/wd/hub/${segment}"))
                        .uri("https://hub-cloud.browserstack.com/wd/hub"))
                .build();
    }
}