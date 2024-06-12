package com.petpals.application.entrypoints.menus;

import com.petpals.domain.ports.in.MenuOptionsIn;
import com.petpals.shared.model.Breed;
import com.petpals.shared.model.Country;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.ArrayList;
import java.util.List;

@Path("/options")
@SecurityRequirement(name = "api_key")
public class MenuOptions {
	
	MenuOptionsIn menuOptionsIn;
	
	public MenuOptions(MenuOptionsIn menuOptionsIn) {
		this.menuOptionsIn = menuOptionsIn;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/countries")
	public List<Country> getCountries() {
		return menuOptionsIn.getCountries();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/breeds")
	public List<Breed> getBreeds() {
		return new ArrayList<>();
	}
}
