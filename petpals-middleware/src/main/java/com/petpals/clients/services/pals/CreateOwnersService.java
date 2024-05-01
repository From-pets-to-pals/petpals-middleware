package com.petpals.clients.services.pals;

import com.petpals.clients.endpoints.pals.CreateOwnersClient;
import com.petpals.clients.mappers.pals.CreateOwnerMapper;
import com.petpals.domain.commands.pals.CreateOwnerCommand;
import com.petpals.domain.ports.out.CreateOwnerOut;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class CreateOwnersService implements CreateOwnerOut {
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
		return createOwnersClient.createOwner(newOwner);
	}
}
