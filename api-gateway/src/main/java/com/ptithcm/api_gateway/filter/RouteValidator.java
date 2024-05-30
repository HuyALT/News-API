package com.ptithcm.api_gateway.filter;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	
	public static final List<String> openApiEndpoints = List.of(
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/otp/**",
            "/eureka",
            "/api/v1/news/**",
            "/api/v1/comment/**",
            "/api/v1/account/setting/canlogin"
    );

    
    public boolean isSecured(ServerHttpRequest request) {
        String path = request.getURI().getPath();
        return openApiEndpoints.stream().noneMatch(path::contains);
    }

    public boolean hasAccess(ServerHttpRequest request, String role) {
        String path = request.getURI().getPath();
        if (path.startsWith("/api/v1/admin/")) {
            return role.equals("ADMIN");
        } else if (path.startsWith("/api/v1/user/")) {
            return role.equals("USER");
        } else if (path.startsWith("/api/v1/account"))
        	return role.equals("ADMIN")||role.equals("USER");
        return true; 
    }
}
