package com.petpals.bootstrap.interceptors;

import io.quarkus.security.UnauthorizedException;
import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.Instant;
import java.util.logging.Logger;

@Provider

public class TestInterceptor implements ContainerRequestFilter {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	@Inject
	JsonWebToken jwt;
	
	@Context
	UriInfo info;
	
	@Context
	HttpServerRequest request;
	private final String authorizedPath = "/login";
	
	@Override
	public void filter(ContainerRequestContext containerRequestContext) {
		if (!info.getPath().equals(authorizedPath) && !hasJwt()) {
			throw new UnauthorizedException("Please login");
		}
	}
	
	private boolean hasJwt() {
		return jwt.getClaimNames() != null;
	}
}
