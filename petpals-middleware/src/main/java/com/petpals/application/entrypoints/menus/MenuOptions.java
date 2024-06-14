package com.petpals.application.entrypoints.menus;

import com.petpals.application.dto.responses.CreateCaregiverOptions;
import com.petpals.application.dto.responses.CreateOwnerOptions;
import com.petpals.application.mappers.options.CreateCaregiverOptionsResponseMapper;
import com.petpals.application.mappers.options.CreateOwnerOptionsResponseMapper;
import com.petpals.domain.ports.in.MenuOptionsIn;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@Path("/options")
@SecurityRequirement(name = "api_key")
public class MenuOptions {
	
	MenuOptionsIn menuOptionsIn;
	
	CreateCaregiverOptionsResponseMapper caregiverOptionsResponseMapper;
	
	CreateOwnerOptionsResponseMapper ownerOptionsResponseMapper;
	
	public MenuOptions(MenuOptionsIn menuOptionsIn, CreateCaregiverOptionsResponseMapper caregiverOptionsResponseMapper, CreateOwnerOptionsResponseMapper ownerOptionsResponseMapper) {
		this.menuOptionsIn = menuOptionsIn;
		this.caregiverOptionsResponseMapper = caregiverOptionsResponseMapper;
		this.ownerOptionsResponseMapper = ownerOptionsResponseMapper;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/create/caregiver")
	public CreateCaregiverOptions getCreateCaregiverMenuOptions() {
		var countries = menuOptionsIn.getCountries();
		var species = menuOptionsIn.getSpecies();
		return caregiverOptionsResponseMapper.toResponse(countries, species);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/create/owner")
	public CreateOwnerOptions getBreeds() {
		var countries = menuOptionsIn.getCountries();
		var species = menuOptionsIn.getSpecies();
		var dogBreeds = menuOptionsIn.getDogBreeds();
		var catBreeds = menuOptionsIn.getCatBreeds();
		var nacBreeds = menuOptionsIn.getNacBreeds();
		return ownerOptionsResponseMapper.toResponse(countries, species, dogBreeds, catBreeds, nacBreeds);
	}
}
