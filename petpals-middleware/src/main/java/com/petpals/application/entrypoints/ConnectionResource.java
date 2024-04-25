package com.petpals.application.entrypoints;

import com.petpals.domain.ports.in.CaregiversHealthCheckIn;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;


@Path("/hello")
public class ConnectionResource {
	
	private final Logger logger = Logger.getLogger(ConnectionResource.class.getName());
	
	CaregiversHealthCheckIn caregiversHealthCheckIn;
	
	public ConnectionResource(CaregiversHealthCheckIn caregiversHealthCheckIn) {
		this.caregiversHealthCheckIn = caregiversHealthCheckIn;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		return caregiversHealthCheckIn.hello();
	}
	
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloRolesAllowed(@PathParam("name") String name) {
		return caregiversHealthCheckIn.helloYou(name);
	}
}
