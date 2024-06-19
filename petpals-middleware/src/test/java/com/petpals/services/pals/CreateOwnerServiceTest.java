package com.petpals.services.pals;

import com.petpals.domain.commands.pals.CreateOwnerCommand;
import com.petpals.domain.commands.pals.CreatePalCommand;
import com.petpals.domain.ports.in.CreateOwnerIn;
import com.petpals.domain.ports.out.CreateOwnerOut;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.model.dto.PalIdentityInformation;
import com.petpals.shared.model.dto.PalMeasurement;
import com.petpals.shared.model.dto.PalMedicalInformation;
import com.petpals.shared.model.enums.SpeciesEnum;
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
				"A",
				"Sidou",
				"OPPO X60",
				UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""),
				"FRANCE",
				List.of(new CreatePalCommand(
						UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""),
								new PalMedicalInformation(
										true,
										new ArrayList<>(),
										null,
										null,
										false
								),
								new PalMeasurement(
										29.0,
										53.5
								),
								new PalIdentityInformation(
										"Ashe",
										"Ashe",
										"2022-04-28",
										true,
										SpeciesEnum.DOG,
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
		Assertions.assertEquals(createOwnerCommand.getReference(),createOwnerCommandCaptor.getValue().getReference());
		Assertions.assertEquals(createOwnerCommand.getEmail(),createOwnerCommandCaptor.getValue().getEmail());
		Assertions.assertEquals(createOwnerCommand.getUsername(),createOwnerCommandCaptor.getValue().getUsername());
		Assertions.assertEquals(createOwnerCommand.getLocation(),createOwnerCommandCaptor.getValue().getLocation());
		Assertions.assertEquals(createOwnerCommand.getDeviceId(),createOwnerCommandCaptor.getValue().getDeviceId());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalIdentityInformation().name(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalIdentityInformation().name());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalIdentityInformation().shortname(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalIdentityInformation().shortname());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getReference(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getReference());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalIdentityInformation().breed(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalIdentityInformation().breed());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalIdentityInformation().specie(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalIdentityInformation().specie());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalIdentityInformation().birthDate(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalIdentityInformation().birthDate());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalIdentityInformation().icadIdentifier(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalIdentityInformation().icadIdentifier());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalIdentityInformation().isMale(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalIdentityInformation().isMale());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalIdentityInformation().hasPassport(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalIdentityInformation().hasPassport());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalMedicalInformation().getIsVaccinated(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalMedicalInformation().getIsVaccinated());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalMedicalInformation().getIsSterilized(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalMedicalInformation().getIsSterilized());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalMedicalInformation().getNextVaccine(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalMedicalInformation().getNextVaccine());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalMedicalInformation().getNextPlannedVetApp(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalMedicalInformation().getNextPlannedVetApp());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalMedicalInformation().getMedicalHistory(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalMedicalInformation().getMedicalHistory());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalMeasurement().height(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalMeasurement().height());
		Assertions.assertEquals(createOwnerCommand.getPals().get(0).getPalMeasurement().weight(),
								createOwnerCommandCaptor.getValue().getPals().get(0).getPalMeasurement().weight());
		Mockito.verifyNoMoreInteractions(createOwnerOut);
	}
}
