package com.petpals.entrypoints;

import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.clients.services.SaveCaregiverService;
import com.petpals.shared.errorhandling.ApplicationExceptions;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Path("/caregivers")
public class CreateCaregiverResource {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RestClient
	SaveCaregiverService saveCaregiverService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createCaregiver(CreateCaregiver createCaregiver){
		if(logger.isInfoEnabled()){
			logger.info("Call create caregiver");
			logger.info(String.format("Create caregiver call with : %s" ,createCaregiver.toString()));
		}
		try {
			return saveCaregiverService.addCaregiver(createCaregiver);
		} catch (Exception e) {
			throw new ApplicationExceptions(ExceptionsEnum.CAREGIVER_API_HELLO_ERROR);
		}
	}
}
