package com.petpals.services.pals;

import com.petpals.domain.commands.pals.CreateOwnerCommand;
import com.petpals.domain.commands.pals.CreatePalCommand;
import com.petpals.domain.ports.in.CreateOwnerIn;
import com.petpals.domain.ports.out.CreateOwnerOut;
import com.petpals.shared.enums.Species;
import com.petpals.shared.model.PalIdentityInformation;
import com.petpals.shared.model.PalMeasurement;
import com.petpals.shared.model.PalMedicalInformation;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@QuarkusTest
class CreateOwnerServiceTest {
	@Inject
	CreateOwnerIn createOwnerIn;
	@InjectMock
	CreateOwnerOut createOwnerOut;
	
	ArgumentCaptor<CreateOwnerCommand> createOwnerCommandCaptor = ArgumentCaptor.forClass(CreateOwnerCommand.class);
	
	private CreateOwnerCommand createOwnerCommand;
	
	@BeforeEach
	public void setup() {
		createOwnerCommand = new CreateOwnerCommand(
				"sa.bennaceur@gmail.com",
				"OPPO X59",
				"FRANCE",
				List.of(new CreatePalCommand(
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
	}
	
	@ParameterizedTest
	@CsvSource(value={
			"1",
							 "2",
							 "3"
							 
	})
	void testCreateOwner(Long expectedValue) {
		Mockito.when(createOwnerOut.createOwners(createOwnerCommand)).thenReturn(expectedValue);
		createOwnerIn.createOwners(createOwnerCommand);
		Mockito.verify(createOwnerOut, Mockito.times(1)).createOwners(createOwnerCommandCaptor.capture());
		Assertions.assertNotNull(createOwnerCommandCaptor.getValue().getReference());
		for(CreatePalCommand pal: createOwnerCommandCaptor.getValue().getPals()) {
			Assertions.assertNotNull(pal.getReference());
		}
	}
}
