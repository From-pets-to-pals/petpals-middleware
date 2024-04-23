package com.petpals.bootstrap.interceptors;

import com.petpals.shared.errorhandling.ApplicationExceptions;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;


@RequestScoped
public class RequestInterceptor implements ContainerRequestFilter  {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	JsonWebToken jwt;
	@Context
	UriInfo info;
	
	@Context
	HttpServerRequest request;
	
	private final String authorizedPath = "/login";
	
	public RequestInterceptor(JsonWebToken jwt) {
		this.jwt = jwt;
	}
	
	@Override
	public void filter(ContainerRequestContext containerRequestContext) {
		if(hasJwt()){
			logger.info(jwt.getName());
		}
		if (!info.getPath().equals(authorizedPath) && !hasJwt()) {
			throw new ApplicationExceptions(ExceptionsEnum.NO_JWT_TOKEN);
		}
	}
	
	private boolean hasJwt() {
		return jwt.getClaimNames() != null;
	}
}
