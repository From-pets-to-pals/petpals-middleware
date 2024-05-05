package com.petpals.clients.endpoints.caregivers;

import com.petpals.bootstrap.factories.CaregiversClientConfigurationFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * To use it via injection.
 * <p>
 * {@code
 *
 * @Inject
 * @RestClient MyRemoteService myRemoteService;
 * <p>
 * public void doSomething() {
 * Set<MyRemoteService.Extension> restClientExtensions = myRemoteService.getExtensionsById("io.quarkus:quarkus-hibernate-validator");
 * }
 * }
 */
@RegisterRestClient(baseUri = "https://petpals-caregivers.azurewebsites.net/")
@RegisterClientHeaders(CaregiversClientConfigurationFactory.class)
@RequestScoped
@Path("/hello")
public interface CaregiversHealthCheckClient {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	String hello();
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	String helloYou(@PathParam("name") String name);
}
