package com.petpals.clients.endpoints;

import com.petpals.bootstrap.factories.PalsClientConfigurationFactory;
import com.petpals.shared.model.Breed;
import com.petpals.shared.model.BreedWithoutSpecie;
import com.petpals.shared.model.Country;
import com.petpals.shared.model.Specie;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "https://petpals-pals.azurewebsites.net/")
@RegisterClientHeaders(PalsClientConfigurationFactory.class)
@RequestScoped
@Path("/options")
public interface MenuOptionsClient {
	@GET
	@Path("/countries")
	@Produces(MediaType.APPLICATION_JSON)
	List<Country> getCountries();
	
	@GET
	@Path("/breeds")
	@Produces(MediaType.APPLICATION_JSON)
	List<Breed> getBreeds();
	@GET
	@Path("/breeds/dogs")
	@Produces(MediaType.APPLICATION_JSON)
	List<BreedWithoutSpecie> getDogBreeds();
	@GET
	@Path("/breeds/cats")
	@Produces(MediaType.APPLICATION_JSON)
	List<BreedWithoutSpecie> getCatBreeds();
	@GET
	@Path("/breeds/nacs")
	@Produces(MediaType.APPLICATION_JSON)
	List<BreedWithoutSpecie> getNacBreeds();
	@GET
	@Path("/species")
	@Produces(MediaType.APPLICATION_JSON)
	List<Specie> getSpecies();
}
