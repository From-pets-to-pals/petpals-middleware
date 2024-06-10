package com.petpals.application.entrypoints.menus;

import com.petpals.application.dto.menus.Country;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.ArrayList;
import java.util.List;

@Path("/options")
@SecurityRequirement(name = "api_key")
public class LocationOptions {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/countries")
	public List<Country> getCountries() {
		return new ArrayList<>();
	}
}
