package com.petpals.application.entrypoints;

import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.logging.Logger;


@Path("/caregivers")
@SecurityRequirement(name = "api_key")
public class CreateCaregiverResource {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	SaveCaregiversIn saveCaregiversIn;
	
	public CreateCaregiverResource(SaveCaregiversIn saveCaregiversIn) {
		this.saveCaregiversIn = saveCaregiversIn;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createCaregiver(CreateCaregiver createCaregiver) {
		logger.info("Call create caregiver");
		if (logger.isInfoEnabled()) {
			logger.info(String.format("Create caregiver call with : %s", createCaregiver.toString()));
		}
		return saveCaregiversIn.createCaregiver(createCaregiver);
	}
}
