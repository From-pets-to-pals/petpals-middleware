package com.petpals.entrypoints;

import com.petpals.clients.services.CaregiverLoginService;
import com.petpals.services.JwtTokenGenerator;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Logger;

@Path("/login")
public class ConnectionResource {
	
	private final Logger logger = Logger.getLogger(ConnectionResource.class.getName());
	JwtTokenGenerator tokenGenerator;
	
	@RestClient
	CaregiverLoginService caregiverLoginService;
	
	public ConnectionResource(JwtTokenGenerator tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@PermitAll
	public String getToken() {
		try {
			logger.info(tokenGenerator.getToken("sa.bennaceur@gmail.com"));
			return caregiverLoginService.hello();
		} catch (Exception e){
			logger.info(e.getMessage());
			throw new RuntimeException("client error");
		}
	}
	
	
	@GET
	@Path("/roles-allowed/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloRolesAllowed(@PathParam("name") String name) {
		try {
			return caregiverLoginService.helloYou(name) + " Hello";
		} catch (Exception e){
			logger.info(e.getMessage());
			throw new RuntimeException("client error");
		}
	}
}
