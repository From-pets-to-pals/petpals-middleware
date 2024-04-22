package com.petpals;

import jakarta.inject.Inject;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Provider

public class TestInterceptor implements ContainerRequestFilter {
	@Inject
	JsonWebToken jwt;
	
	@Context
	UriInfo uriInfo;
	private final String authorizedPath = "/login";
	@Override
	public void filter(ContainerRequestContext containerRequestContext) {
		var securityContext = containerRequestContext.getSecurityContext();
		
		System.out.println(uriInfo.getPath());
		if (!uriInfo.getPath().equals(authorizedPath)) {
			if (!hasJwt()) {
				throw new InternalServerErrorException("Please login");
			} else {
				if (!securityContext.getUserPrincipal().getName().equals(jwt.getName())) {
					throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
				}
			}
			
		}
	}
	
	private boolean hasJwt() {
		return jwt.getClaimNames() != null;
	}
}
