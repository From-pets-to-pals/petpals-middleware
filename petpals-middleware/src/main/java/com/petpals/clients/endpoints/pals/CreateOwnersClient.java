package com.petpals.clients.endpoints.pals;

import com.petpals.bootstrap.factories.PalsClientConfigurationFactory;
import com.petpals.clients.dto.pals.NewOwner;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://petpals-pals.azurewebsites.net/")
@RegisterClientHeaders(PalsClientConfigurationFactory.class)
@RequestScoped
@Path("/owners")
public interface CreateOwnersClient {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	Long createOwner(NewOwner newOwner);
}
