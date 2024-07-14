package com.petpals.application.entrypoints.caregivers;

import com.petpals.application.dto.caregivers.CreateCaregiverRequest;
import com.petpals.application.dto.responses.CreateCaregiverCommandMapper;
import com.petpals.application.mappers.caregivers.CreateCaregiverRequestMapper;
import com.petpals.clients.dto.caregivers.CreateCaregiver;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import jakarta.annotation.security.PermitAll;
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
	private static final Logger LOGGER = Logger.getLogger(CreateCaregiverResource.class);
	
	SaveCaregiversIn saveCaregiversIn;
	CreateCaregiverRequestMapper createCaregiverRequestMapper;

	public CreateCaregiverResource(SaveCaregiversIn saveCaregiversIn, CreateCaregiverRequestMapper createCaregiverRequestMapper) {
		this.saveCaregiversIn = saveCaregiversIn;
		this.createCaregiverRequestMapper = createCaregiverRequestMapper;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@PermitAll
	public String createCaregiver(CreateCaregiverRequest createCaregiver) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Create caregiver call with caregiver : %s", createCaregiver.toString()));
		}
		return saveCaregiversIn.createCaregiver(createCaregiverRequestMapper.toCommand(createCaregiver));
	}
}
