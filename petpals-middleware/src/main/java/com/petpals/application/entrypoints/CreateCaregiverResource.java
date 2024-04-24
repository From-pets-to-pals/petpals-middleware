package com.petpals.application.entrypoints;

import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.clients.endpoints.SaveCaregiversClient;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import com.petpals.shared.errorhandling.ApplicationExceptions;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;


@Path("/caregivers")
public class CreateCaregiverResource {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	SaveCaregiversIn saveCaregiversIn;
	
	public CreateCaregiverResource(SaveCaregiversIn saveCaregiversIn) {
		this.saveCaregiversIn = saveCaregiversIn;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createCaregiver(CreateCaregiver createCaregiver){
		logger.info("Call create caregiver");
		if(logger.isInfoEnabled()){
			logger.info(String.format("Create caregiver call with : %s" ,createCaregiver.toString()));
		}
		try {
			return saveCaregiversIn.createCaregiver(createCaregiver);
		} catch (ResteasyClientErrorException e) {
			throw new ApplicationExceptions(ExceptionsEnum.CAREGIVER_API_HELLO_ERROR);
		}
	}
}
