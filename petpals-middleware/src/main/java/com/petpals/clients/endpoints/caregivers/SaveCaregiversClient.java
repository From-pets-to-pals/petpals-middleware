package com.petpals.clients.endpoints.caregivers;

import com.petpals.bootstrap.factories.CaregiversClientConfigurationFactory;
import com.petpals.clients.dto.caregivers.CreateCaregiver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://petpals-caregivers.azurewebsites.net/")
@RegisterClientHeaders(CaregiversClientConfigurationFactory.class)
@RequestScoped
@Path("/caregivers")
public interface SaveCaregiversClient {
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	String createCaregiver(CreateCaregiver caregiver);
}
