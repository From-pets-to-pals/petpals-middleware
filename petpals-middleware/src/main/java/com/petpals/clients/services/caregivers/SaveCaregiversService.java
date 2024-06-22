package com.petpals.clients.services.caregivers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.clients.endpoints.caregivers.SaveCaregiversClient;
import com.petpals.domain.commands.caregivers.CreateCaregiverCommand;
import com.petpals.domain.ports.out.SaveCaregiversOut;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

@ApplicationScoped
public class SaveCaregiversService implements SaveCaregiversOut {
	private final Logger logger = Logger.getLogger(SaveCaregiversService.class);
	
	SaveCaregiversClient saveCaregiversClient;
	@Inject
	public SaveCaregiversService(@RestClient SaveCaregiversClient saveCaregiversClient) {
		this.saveCaregiversClient = saveCaregiversClient;
	}
	
	@Override
	public void createCaregiver(CreateCaregiverCommand createCaregiver) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			logger.info(mapper.writeValueAsString(createCaregiver));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		try {
			saveCaregiversClient.createCaregiver(createCaregiver);
			
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
}
