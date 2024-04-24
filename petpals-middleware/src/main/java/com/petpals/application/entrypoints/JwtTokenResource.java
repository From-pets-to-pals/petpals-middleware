package com.petpals.application.entrypoints;

import com.petpals.domain.services.JwtTokenGenerator;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/token")
public class JwtTokenResource {
	private static final Logger logger = Logger.getLogger(JwtTokenResource.class);
	
	JwtTokenGenerator tokenGenerator;
	
	public JwtTokenResource(JwtTokenGenerator tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@PermitAll
	@Path("/{email}/{caregiverType}")
	public String getToken(@PathParam("email") String email, @PathParam("caregiverType")  String caregiverType) {
		return tokenGenerator.getToken(email, caregiverType);
	}
}
