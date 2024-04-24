package com.petpals.application.entrypoints;

import com.petpals.clients.endpoints.CaregiversHealthCheckClient;
import com.petpals.domain.ports.in.CaregiversHealthCheckIn;
import com.petpals.domain.services.JwtTokenGenerator;
import com.petpals.shared.errorhandling.ApplicationExceptions;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import io.quarkus.cache.CacheResult;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;


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
		try {
			return caregiversHealthCheckIn.hello();
		} catch (ResteasyClientErrorException e) {
			logger.info(e.getMessage());
			throw new ApplicationExceptions(ExceptionsEnum.CAREGIVER_API_HELLO_ERROR);
		}
	}
	
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloRolesAllowed(@PathParam("name") String name) {
		try {
			return caregiversHealthCheckIn.helloYou(name);
		} catch (ResteasyClientErrorException e) {
			logger.info(e.toString());
			throw new ApplicationExceptions(ExceptionsEnum.CAREGIVER_API_HELLO_ERROR);
		}
	}
}
