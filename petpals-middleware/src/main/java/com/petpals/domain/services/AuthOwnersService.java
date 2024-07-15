package com.petpals.domain.services;

import com.petpals.domain.commands.pals.AuthOwnerCommand;
import com.petpals.domain.ports.in.AuthOwnerIn;
import com.petpals.domain.ports.out.AuthOwnerOut;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AuthOwnersService implements AuthOwnerIn {
	private static final Logger LOGGER = Logger.getLogger(AuthOwnersService.class.getName());
	private final AuthOwnerOut authOwnerOut;

	public AuthOwnersService(AuthOwnerOut authOwnerOut) {
		this.authOwnerOut = authOwnerOut;
	}

	@Override
	public String authOwners(AuthOwnerCommand authOwnerCommand) {

		LOGGER.log(Level.INFO, () -> String.format("authing"));

		return authOwnerOut.authOwners(authOwnerCommand);

	}
}
