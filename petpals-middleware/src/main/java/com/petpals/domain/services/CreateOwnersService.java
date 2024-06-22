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

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class CreateOwnersService implements CreateOwnerIn {
	private static final Logger LOGGER = Logger.getLogger(CreateOwnersService.class.getName());
	private final CreateOwnerOut createOwnerOut;
	
	public CreateOwnersService(CreateOwnerOut createOwnerOut) {
		this.createOwnerOut = createOwnerOut;
	}
	
	@Override
	public String createOwners(CreateOwnerCommand createOwnerCommand) {
		LOGGER.info("Creating owner");
		final var reference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
		createOwnerCommand.setReference(reference);
		
		LOGGER.log(Level.INFO, () -> String.format("Attributing %s as a reference for owner %s", reference,
											createOwnerCommand));
		LOGGER.log(Level.INFO, () -> String.format("Owner %s has %d pals", createOwnerCommand.getEmail(),
								  createOwnerCommand.getPals().size()));
		
		if(!createOwnerCommand.getPals().isEmpty()) {
			for (CreatePalCommand pal : createOwnerCommand.getPals()) {
				final var palReference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "");
				LOGGER.info(() -> String.format("Attributing %s as a reference for pal %s", palReference,
						pal));
				pal.setReference(palReference);
			}
		}
		createOwnerOut.createOwners(createOwnerCommand);
		return reference;
	}
}
