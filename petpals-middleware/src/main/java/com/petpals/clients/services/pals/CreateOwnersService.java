package com.petpals.clients.services.pals;

import com.petpals.clients.endpoints.pals.CreateOwnersClient;
import com.petpals.clients.mappers.pals.CreateOwnerMapper;
import com.petpals.domain.commands.pals.CreateOwnerCommand;
import com.petpals.domain.ports.out.CreateOwnerOut;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

@ApplicationScoped
public class CreateOwnersService implements CreateOwnerOut {
	private static final Logger LOGGER = Logger.getLogger(CreateOwnersService.class);
	@Inject
	@RestClient
	CreateOwnersClient createOwnersClient;
	CreateOwnerMapper createOwnerMapper;
	
	public CreateOwnersService(CreateOwnerMapper createOwnerMapper) {
		this.createOwnerMapper = createOwnerMapper;
	}
	
	@Override
	public Long createOwners(CreateOwnerCommand createOwnerCommand) {
		var newOwner = createOwnerMapper.fromDomain(createOwnerCommand);
		try {
			return createOwnersClient.createOwner(newOwner);
		} catch (ResteasyWebApplicationException e) {
			LOGGER.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.PALS_OFFLINE_REST_CLIENT_EXCEPTION
			);
		} catch (ResteasyClientErrorException e) {
			LOGGER.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.PALS_MISSING_API_KEY);
		}
	}
}
