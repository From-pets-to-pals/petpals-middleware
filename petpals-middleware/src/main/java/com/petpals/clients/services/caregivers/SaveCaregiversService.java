package com.petpals.clients.services.caregivers;

import com.petpals.clients.dto.caregivers.CreateCaregiver;
import com.petpals.clients.endpoints.caregivers.SaveCaregiversClient;
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
	
	@RestClient
	@Inject
	SaveCaregiversClient saveCaregiversClient;
	
	@Override
	public String createCaregiver(CreateCaregiver createCaregiver) {
		try {
			return saveCaregiversClient.createCaregiver(createCaregiver);
			
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
