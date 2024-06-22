package com.petpals.application.entrypoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@Path("/res")
@SecurityRequirement(name = "api_key")
public class Resource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/nojwtoken")
	public String get() {
		return "guette";
	}
}
