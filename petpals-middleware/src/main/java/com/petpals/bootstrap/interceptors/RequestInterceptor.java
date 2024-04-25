package com.petpals.bootstrap.interceptors;

import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.List;


@Provider
public class RequestInterceptor implements ContainerRequestFilter  {
	public static final Logger log = Logger.getLogger(RequestInterceptor.class.getName());
	
	JsonWebToken jwt;
	@Context
	UriInfo info;
	
	
	@Inject
	public RequestInterceptor(JsonWebToken jwt) {
		this.jwt = jwt;
	}
	
	@Override
	public void filter(ContainerRequestContext containerRequestContext) {
		log.info(String.format("Filtering incoming request with uri: %s", info.getPath()));
	 	final List<String> authorizedPath = List.of("/hello", "/token");
		 if(authorizedPath.stream().anyMatch(path -> info.getPath().startsWith(path)) || info.getPath().equals("/caregivers")){
			 return;
		 }
		if(containerRequestContext.getHeaderString("API-KEY") != null && !containerRequestContext.getHeaderString(
				"API-KEY").equals("pals")) {
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_MISSING_API_KEY);
		}
		if(containerRequestContext.getHeaderString("API-KEY") == null && hasJwt()) {
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_MISSING_API_KEY);
		}
		if (!hasJwt()) {
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_NO_JW_TOKEN);
		}
	}
	
	private boolean hasJwt() {
		return jwt.getClaimNames() != null;
	}
}
