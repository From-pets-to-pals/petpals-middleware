package com.petpals.services;

import com.petpals.clients.dto.caregivers.Days;
import com.petpals.domain.commands.caregivers.CreateCaregiverCommand;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import com.petpals.domain.ports.out.SaveCaregiversOut;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.model.enums.PalsFriendsTypes;
import com.petpals.shared.model.enums.SpeciesEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class CreateCaregiversServiceTest {
	
	@Inject
	SaveCaregiversIn saveCaregiversIn;
	@InjectMock
	SaveCaregiversOut saveCaregiversOut;
	
	@Test
	void shouldReturnReferenceUponCreateCaregivers(){
		CreateCaregiverCommand createCaregiver = new CreateCaregiverCommand();
		createCaregiver.setReference(UUIDGenerator.generateUUID().toString());
		createCaregiver.setFirstName("Sid");
		createCaregiver.setLastName("Bennaceur");
		createCaregiver.setEmail("sa.benn@test.com");
		createCaregiver.setPhoneNumber("0764017528");
		createCaregiver.setAddress("101, rue des Acquevilles");
		createCaregiver.setCity("Suresnes");
		createCaregiver.setZipCode("92150");
		createCaregiver.setCountry("France");
		createCaregiver.setWorkingDays(new Days[]{});
		createCaregiver.setPalsHandled(new SpeciesEnum[]{});
		createCaregiver.setHomeService(false);
		createCaregiver.setAppointmentDuration(0.5);
		createCaregiver.setCaregiverType(PalsFriendsTypes.VET);
		createCaregiver.setIsSubscribed(false);
		createCaregiver.setPriceRating(0.0);
		createCaregiver.setServiceRating(0.0);
		UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
		Mockito.doNothing().when(saveCaregiversOut).createCaregiver(createCaregiver);
		saveCaregiversIn.createCaregiver(createCaregiver);
		Mockito.verify(saveCaregiversOut, Mockito.times(1)).createCaregiver(createCaregiver);
	}
}
