package com.petpals.clients.endpoints.caregivers;

import com.petpals.bootstrap.factories.CaregiversClientConfigurationFactory;
import com.petpals.clients.dto.caregivers.CreateCaregiver;
import com.petpals.domain.commands.CreateCaregiverCommand;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://127.0.0.1:83/")
@RegisterClientHeaders(CaregiversClientConfigurationFactory.class)
@RequestScoped
@Path("/caregivers")
public interface SaveCaregiversClient {
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	String createCaregiver(CreateCaregiverCommand caregiver);
}
