package com.petpals.clients.caregivers;

import com.petpals.clients.dto.caregivers.Days;
import com.petpals.clients.endpoints.caregivers.SaveCaregiversClient;
import com.petpals.domain.commands.CreateCaregiverCommand;
import com.petpals.domain.ports.out.SaveCaregiversOut;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import com.petpals.shared.model.enums.PalsFriendsTypes;
import com.petpals.shared.model.enums.SpeciesEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class CreateCaregiversClientTest {
	
	@Inject
	SaveCaregiversOut saveCaregiversOut;
	@InjectMock
	@RestClient
	SaveCaregiversClient saveCaregiversClient;
	CreateCaregiverCommand createCaregiver;
	@BeforeEach
	void setUp(){
		createCaregiver = new CreateCaregiverCommand();
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
		createCaregiver.setSubscribed(false);
		createCaregiver.setPriceRating(0.0);
		createCaregiver.setServiceRating(0.0);
	}
	
	@Test
	void shouldReturnReferenceUponCreateCaregivers(){
		var toReturn = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
		Mockito.when(saveCaregiversClient.createCaregiver(createCaregiver)).thenReturn(toReturn);
		var res = saveCaregiversOut.createCaregiver(createCaregiver);
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldThrowRestWebException(){
		Mockito.when(saveCaregiversClient.createCaregiver(createCaregiver)).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> saveCaregiversOut.createCaregiver(createCaregiver));
		Mockito.verify(saveCaregiversClient).createCaregiver(createCaregiver);
	}
	
	@Test
	void shouldThrowRestClientException(){
		Mockito.when(saveCaregiversClient.createCaregiver(createCaregiver)).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> saveCaregiversOut.createCaregiver(createCaregiver));
		Mockito.verify(saveCaregiversClient).createCaregiver(createCaregiver);
	}
}
