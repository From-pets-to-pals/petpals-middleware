package com.petpals.application.entrypoints;

import com.petpals.clients.dto.caregivers.CreateCaregiver;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;


@Path("/caregivers")
public class CreateCaregiverResource {
	private static final Logger LOGGER = Logger.getLogger(CreateCaregiverResource.class);
	
	SaveCaregiversIn saveCaregiversIn;
	
	public CreateCaregiverResource(SaveCaregiversIn saveCaregiversIn) {
		this.saveCaregiversIn = saveCaregiversIn;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createCaregiver(CreateCaregiver createCaregiver) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Create caregiver call with caregiver : %s", createCaregiver.toString()));
		}
		return saveCaregiversIn.createCaregiver(createCaregiver);
	}
}
