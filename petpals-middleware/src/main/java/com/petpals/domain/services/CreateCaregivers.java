package com.petpals.domain.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.clients.dto.caregivers.CreateCaregiver;
import com.petpals.domain.commands.CreateCaregiverCommand;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import com.petpals.domain.ports.out.SaveCaregiversOut;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CreateCaregivers implements SaveCaregiversIn {
	
	SaveCaregiversOut saveCaregiversOut;
	
	public CreateCaregivers(SaveCaregiversOut saveCaregiversOut) {
		this.saveCaregiversOut = saveCaregiversOut;
	}
	
	@Override
	public String createCaregiver(CreateCaregiverCommand createCaregiver) {
		final var reference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "");
		createCaregiver.setReference(reference);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Logger.getLogger("here").info(mapper.writeValueAsString(createCaregiver));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return saveCaregiversOut.createCaregiver(createCaregiver);
	}
}
