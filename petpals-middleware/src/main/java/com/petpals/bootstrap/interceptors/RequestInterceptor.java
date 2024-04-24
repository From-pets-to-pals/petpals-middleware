package com.petpals.bootstrap.interceptors;

import com.petpals.shared.errorhandling.ApplicationExceptions;
import com.petpals.shared.errorhandling.ExceptionsEnum;
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
	 	final List<String> authorizedPath = List.of("/login","/caregivers");
		if(containerRequestContext.getHeaderString("API-KEY") != null && !containerRequestContext.getHeaderString(
				"API-KEY").equals("pals")) {
			System.out.println("their");
			
			throw new ApplicationExceptions(ExceptionsEnum.CAREGIVER_MIDDLEWARE_MISSING_API_KEY);
		}
		if(containerRequestContext.getHeaderString("API-KEY") == null && hasJwt()) {
			
			throw new ApplicationExceptions(ExceptionsEnum.CAREGIVER_MIDDLEWARE_MISSING_API_KEY);
		}
		System.out.println("there");
		
		if(hasJwt()){
			log.info(jwt.getName());
		}
		if (!authorizedPath.contains(info.getPath()) && !hasJwt()) {
			System.out.println("here");
			throw new ApplicationExceptions(ExceptionsEnum.NO_JWT_TOKEN);
		}
	}
	
	private boolean hasJwt() {
		return jwt.getClaimNames() != null;
	}
}
