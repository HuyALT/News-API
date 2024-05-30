package com.ptithcm.api_gateway.filter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.ptithcm.api_gateway.service.AccountService;
import com.ptithcm.api_gateway.util.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AccountService accountService;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();
			if (validator.isSecured(request)) {
				String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
				if (authHeader == null || !authHeader.startsWith("Bearer ")) {
					response.setStatusCode(HttpStatus.FORBIDDEN);
					return response.setComplete();
				}
				String token = Objects.requireNonNull(authHeader).substring(7);

				try {
					jwtUtil.validateToken(token);
				} catch (Exception e) {
					response.setStatusCode(HttpStatus.FORBIDDEN);
					return response.setComplete();
				}

				String role = jwtUtil.extractRole(token);

				if (!validator.hasAccess(request, role)) {

					response.setStatusCode(HttpStatus.FORBIDDEN);
					return response.setComplete();

				}

				return Mono.defer(() -> accountService.callExternalService(token)).flatMap(res -> {
					if (res.statusCode().equals(HttpStatus.OK)) {
						return chain.filter(exchange);
					} else {
						return errorResponse(response, HttpStatus.FORBIDDEN);
					}
				});

			}
			return chain.filter(exchange);
		};
	}

	public static class Config {
	}

	private Mono<Void> errorResponse(ServerHttpResponse response, HttpStatus status) {
		response.setStatusCode(status);
		return response.setComplete();
	}
}
