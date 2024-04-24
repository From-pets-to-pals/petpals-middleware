package com.petpals.clients.services;

import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.clients.endpoints.SaveCaregiversClient;
import com.petpals.domain.ports.out.SaveCaregiversOut;
import com.petpals.domain.services.JwtTokenGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class SaveCaregiversService implements SaveCaregiversOut {
	@RestClient
	@Inject
	SaveCaregiversClient saveCaregiversClient;
	
	@Override
	public String createCaregiver(CreateCaregiver createCaregiver) {
		return saveCaregiversClient.createCaregiver(createCaregiver);
	}
}
