package com.petpals.clients.pals;

import com.petpals.clients.dto.pals.NewOwner;
import com.petpals.clients.endpoints.pals.CreateOwnersClient;
import com.petpals.clients.mappers.pals.CreateOwnerMapper;
import com.petpals.domain.commands.pals.CreateOwnerCommand;
import com.petpals.domain.commands.pals.CreatePalCommand;
import com.petpals.domain.ports.out.CreateOwnerOut;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.enums.Species;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import com.petpals.shared.model.PalIdentityInformation;
import com.petpals.shared.model.PalMeasurement;
import com.petpals.shared.model.PalMedicalInformation;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@QuarkusTest
class CreateOwnersClientTest {
	
	@Inject
	CreateOwnerOut createOwnerOut;
	
	@InjectMock
	@RestClient
	CreateOwnersClient createOwnersClient;
	
	@Inject
	CreateOwnerMapper	createOwnerMapper;
	
	
	private CreateOwnerCommand createOwnerCommand;
	
	private NewOwner newOwner;
	@BeforeEach
	public void setup() {
		var reference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,"");
		createOwnerCommand = new CreateOwnerCommand(
				"sa.bennaceur@gmail.com",
				"OPPO X59",
				reference,
				"FRANCE",
				List.of(new CreatePalCommand(
						reference,
						new PalMedicalInformation(
								true,
								new ArrayList<>(),
								new Date(),
								new Date(),
								false
						),
						new PalMeasurement(
								29.0,
								53.5
						),
						new PalIdentityInformation(
								"Ashe",
								"Ashe",
								new Date(),
								true,
								Species.DOG,
								"Husky",
								"250261245784512",
								true)
								
						)
				)
		);
		newOwner = createOwnerMapper.fromDomain(createOwnerCommand);
		
		System.out.println(newOwner);
	}
	@Test
	void shouldReturnIdUponCreateCaregivers(){
		var toReturn = 1L;
		Mockito.when(createOwnersClient.createOwner(newOwner)).thenReturn(toReturn);
		var res = createOwnerOut.createOwners(createOwnerCommand);
		Assertions.assertEquals(toReturn, res);
	}
	
	
	@Test
	void shouldThrowRestWebException(){
		Mockito.when(createOwnersClient.createOwner(newOwner)).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(PetPalsExceptions.class,
								() -> createOwnerOut.createOwners(createOwnerCommand));
		Mockito.verify(createOwnersClient).createOwner(newOwner);
	}
	
	@Test
	void shouldThrowRestClientException(){
		Mockito.when(createOwnersClient.createOwner(newOwner)).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> createOwnerOut.createOwners(createOwnerCommand));
		Mockito.verify(createOwnersClient).createOwner(newOwner);
	}
}
