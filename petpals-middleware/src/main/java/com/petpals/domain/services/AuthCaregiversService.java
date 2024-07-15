package com.petpals.domain.services;

import com.petpals.domain.commands.caregivers.AuthCaregiverCommand;
import com.petpals.domain.commands.pals.AuthOwnerCommand;
import com.petpals.domain.ports.in.AuthCaregiverIn;
import com.petpals.domain.ports.in.AuthOwnerIn;
import com.petpals.domain.ports.out.AuthCaregiverOut;
import com.petpals.domain.ports.out.AuthOwnerOut;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AuthCaregiversService implements AuthCaregiverIn {
	private static final Logger LOGGER = Logger.getLogger(AuthCaregiversService.class.getName());
	private final AuthCaregiverOut authCaregiverOut;

	public AuthCaregiversService(AuthOwnerOut authOwnerOut) {

		this.authCaregiverOut = authCaregiverOut;
	}

	@Override
	public String authCaregivers(AuthCaregiverCommand authCaregiverCommand) {

		LOGGER.log(Level.INFO, () -> String.format("authing"));

		return authCaregiverOut.authCaregivers(authCaregiverCommand);

	}
}
