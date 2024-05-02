package com.petpals.bootstrap.interceptors;

import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.List;


@Provider
public class RequestInterceptor implements ContainerRequestFilter  {
	public static final Logger LOGGER = Logger.getLogger(RequestInterceptor.class.getName());
	
	JsonWebToken jwt;
	@Context
	UriInfo info;
	
	private static final String HEADER_NAME = "API-KEY";
	
	@Inject
	public RequestInterceptor(JsonWebToken jwt) {
		this.jwt = jwt;
	}
	@ConfigProperty(name = "claims.origin")
	public String origin;
	@ConfigProperty(name = "middleware.api.key")
	public String middlewareApiKey;
	@Override
	public void filter(ContainerRequestContext containerRequestContext) {
		LOGGER.info(String.format("Filtering incoming request with uri: %s", info.getPath()));
	 	final List<String> authorizedPath = List.of("/hello", "/token");
		 if(authorizedPath.stream().anyMatch(path -> info.getPath().startsWith(path)) || info.getPath().equals(
				 "/caregivers") || info.getPath().equals("/owners")){
			 if(containerRequestContext.getHeaderString(HEADER_NAME) == null || !containerRequestContext.getHeaderString(
					 HEADER_NAME).equals(middlewareApiKey)) {
				 throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_MISSING_API_KEY);
			 }
			 return;
		 }
		if(containerRequestContext.getHeaderString(HEADER_NAME) != null && !containerRequestContext.getHeaderString(
				HEADER_NAME).equals(middlewareApiKey)) {
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_MISSING_API_KEY);
		}
		if(containerRequestContext.getHeaderString(HEADER_NAME) == null && hasJwt()) {
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_MISSING_API_KEY);
		}
		if (!hasJwt()) {
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_NO_JW_TOKEN);
		}
		if(hasJwt() && !jwt.containsClaim(Claims.address.name())){
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_NO_JW_TOKEN);
		}
		if (hasJwt() && jwt.containsClaim(Claims.address.name()) && (jwt.getClaim(Claims.address.name()) == null || !jwt.getClaim(Claims.address.name()).equals(origin))) {
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_NO_JW_TOKEN);
		}
	}
	
	private boolean hasJwt() {
		return jwt.getClaimNames() != null;
	}
}
