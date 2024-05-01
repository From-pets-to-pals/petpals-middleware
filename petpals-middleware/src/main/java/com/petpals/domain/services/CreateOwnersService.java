package com.petpals.domain.services;

import com.petpals.domain.commands.pals.CreateOwnerCommand;
import com.petpals.domain.commands.pals.CreatePalCommand;
import com.petpals.domain.ports.in.CreateOwnerIn;
import com.petpals.domain.ports.out.CreateOwnerOut;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.logging.Logger;

@ApplicationScoped
public class CreateOwnersService implements CreateOwnerIn {
	private static final Logger LOGGER = Logger.getLogger(CreateOwnersService.class.getName());
	private final CreateOwnerOut createOwnerOut;
	
	public CreateOwnersService(CreateOwnerOut createOwnerOut) {
		this.createOwnerOut = createOwnerOut;
	}
	
	@Override
	public void createOwners(CreateOwnerCommand createOwnerCommand) {
		LOGGER.info("Creating owner");
		var reference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
		createOwnerCommand.setReference(reference);
		LOGGER.info(String.format("Attributing %s as a reference for owner %s", reference, createOwnerCommand));
		LOGGER.info(String.format("Owner %s has %d pals", createOwnerCommand.getEmail(),
								  createOwnerCommand.getPals().size()));
		
		if(!createOwnerCommand.getPals().isEmpty()){
			for(CreatePalCommand pal: createOwnerCommand.getPals()){
				reference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
				LOGGER.info(String.format("Attributing %s as a reference for pal %s", reference, createOwnerCommand));
				pal.setReference(reference);
			}
			createOwnerOut.createOwners(createOwnerCommand);
		} else {
			throw new PetPalsExceptions(ExceptionsEnum.MIDDLEWARE_CREATE_OWNER_NO_PAL);
		}
	}
}
