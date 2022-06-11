package com.ne.apigateway.security;

import com.ne.clients.authentification.JwtChecks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private final String uri = "http://localhost:8086/api/authentification/validate";
    private final RestTemplate restTemplate = new RestTemplate();

    public AuthorizationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        JwtChecks checks = new JwtChecks();

        return (exchange, chain) -> {

            ArrayList<String> adminURI = new ArrayList<>();
            ArrayList<String> userURI = new ArrayList<>();

            adminURI.add("http://localhost:8083/api/authentification/login");
            userURI.add("http://localhost:8083/api/authentification/login");

            String APITargeted = String.valueOf(exchange.getRequest().getURI());

            checks.setToken(exchange.getRequest().getHeaders().getFirst("Authorization"));
            String role = restTemplate.postForObject(uri, checks, String.class);

            if (Objects.equals(role, "SUPERADMIN")) {
                return chain.filter(exchange);
            }

            else if (Objects.equals(role, "ADMIN")) {
                if (adminURI.contains(APITargeted) || userURI.contains(APITargeted)) {
                    return chain.filter(exchange);
                } else {
                    throw new RuntimeException();
                }
            }

            else if (Objects.equals(role, "USER")) {
                if (userURI.contains(APITargeted)) {
                    return chain.filter(exchange);
                }
            }

            else {
                return chain.filter(exchange);
            }


            return chain.filter(exchange);
        };

    }

    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
