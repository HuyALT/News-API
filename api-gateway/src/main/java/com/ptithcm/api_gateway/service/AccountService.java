package com.ptithcm.api_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class AccountService {
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	public Mono<ClientResponse> callExternalService(String token) {
        return webClientBuilder.build()
                .get()
                .uri(uriBuilder->uriBuilder
                		.scheme("http")
                		.host("News-user-service")
                		.path("/api/v1/account/setting/canlogin")
                		.queryParam("token", token)
                		.build())
                .exchangeToMono(response->Mono.just(response));
    }
	
}
