package com.petpals;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpException;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Logger;

@Path("/login")
public class ConnectionResource {
	
	private final Logger logger = Logger.getLogger(ConnectionResource.class.getName());
	JwtTokenGenerator tokenGenerator;
	
	@RestClient
	MyRemoteService myRemoteService;
	
	public ConnectionResource(JwtTokenGenerator tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@PermitAll
	public String getToken() {
		try {
			return myRemoteService.login() + " " + tokenGenerator.getToken("sa.bennaceur@gmail.com");
		} catch (Exception e){
			logger.info(e.getMessage());
			throw new RuntimeException("client error");
		}
	}
	
	
	@GET
	@Path("roles-allowed")
	@RolesAllowed({ "Owners", "Caregivers" })
	@Produces(MediaType.TEXT_PLAIN)
	public String helloRolesAllowed() {
		try {
			return myRemoteService.login() + " Hello";
		} catch (Exception e){
			logger.info(e.getMessage());
			throw new RuntimeException("client error");
		}
		
	}
	
	
}
