package com.petpals.domain.services;

import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import com.petpals.domain.ports.out.SaveCaregiversOut;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateCaregivers implements SaveCaregiversIn {
	
	SaveCaregiversOut saveCaregiversOut;
	
	public CreateCaregivers(SaveCaregiversOut saveCaregiversOut) {
		this.saveCaregiversOut = saveCaregiversOut;
	}
	
	@Override
	public String createCaregiver(CreateCaregiver createCaregiver) {
		return saveCaregiversOut.createCaregiver(createCaregiver);
	}
}
