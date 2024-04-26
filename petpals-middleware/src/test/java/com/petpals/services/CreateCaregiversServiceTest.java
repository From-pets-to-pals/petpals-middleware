package com.petpals.services;

import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.clients.dto.Days;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import com.petpals.domain.ports.out.SaveCaregiversOut;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
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
		CreateCaregiver createCaregiver = new CreateCaregiver(
				"Sid",
				"Bennaceur",
				null,
				"0764017528",
				"101, rue des Acquevilles",
				"Suresnes",
				"92150",
				"France",
				new Days[]{},
				new Species[]{},
				false,
				0.5,
				CaregiverTypes.GROOMER,
				false,
				0.0,
				0.0
		);
		var toReturn = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
		Mockito.when(saveCaregiversOut.createCaregiver(createCaregiver)).thenReturn(toReturn);
		var res = saveCaregiversIn.createCaregiver(createCaregiver);
		Assertions.assertEquals(toReturn, res);
	}
}
