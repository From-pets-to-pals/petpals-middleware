package com.petpals.application.entrypoints.caregivers;

import com.petpals.domain.ports.in.CaregiversHealthCheckIn;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.logging.Logger;


@Path("/hello")
@SecurityRequirement(name = "api_key")
public class CaregiversHealthCheckResource {
	
	private static final Logger LOGGER = Logger.getLogger(CaregiversHealthCheckResource.class.getName());
	
	CaregiversHealthCheckIn caregiversHealthCheckIn;
	
	public CaregiversHealthCheckResource(CaregiversHealthCheckIn caregiversHealthCheckIn) {
		this.caregiversHealthCheckIn = caregiversHealthCheckIn;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("Call get Hello");
		}
		return caregiversHealthCheckIn.hello();
	}
	
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloRolesAllowed(@PathParam("name") String name) {
		if(LOGGER.isInfoEnabled()){
			LOGGER.info(String.format("Call get Hello %s", name));
		}
		return caregiversHealthCheckIn.helloYou(name);
	}
}
