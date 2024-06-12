package com.petpals.clients.endpoints;

import com.petpals.bootstrap.factories.CaregiversClientConfigurationFactory;
import com.petpals.shared.model.Breed;
import com.petpals.shared.model.Country;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "https://petpals-caregivers.azurewebsites.net/")
@RegisterClientHeaders(CaregiversClientConfigurationFactory.class)
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
}
