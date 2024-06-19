package com.petpals.clients.endpoints.pals;

import com.petpals.bootstrap.factories.PalsClientConfigurationFactory;
import com.petpals.clients.dto.pals.AuthOwner;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://127.0.0.1:70")
@RegisterClientHeaders(PalsClientConfigurationFactory.class)
@RequestScoped
@Path("/owners/auth")
public interface AuthOwnersClient {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	String authOwner(AuthOwner authOwner);
}
