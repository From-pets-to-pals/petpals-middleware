package com.petpals;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

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
public interface MyRemoteService {
	
	@GET
	@Path("/hello")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	String login();
	
	class Extension {
		public String id;
		public String name;
		public String shortName;
		public List<String> keywords;
	}
}
