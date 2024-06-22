package com.petpals.application.entrypoints.jwt;

import com.petpals.domain.services.JwtTokenGenerator;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/token")
public class JwtTokenResource {
	JwtTokenGenerator tokenGenerator;
	
	public JwtTokenResource(JwtTokenGenerator tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@PermitAll
	@Path("/{email}/{userType}")
	public String getToken(@PathParam("email") String email, @PathParam("userType") String userType) {
		return tokenGenerator.getToken(email, userType);
	}
}
