package com.petpals;

import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.clients.dto.Days;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateCaregiverTest {
	@Test
	void shouldCheckObjectsIntegrity(){
		var days = 				new Days[]{};
		var speecies = 				new Species[]{};
		
		var createCaregiver = new CreateCaregiver(
				"Sid",
				"Bennaceur",
				"sa.bennaceur@test-db-test.com",
				"0764017528",
				"101, rue des Acquevilles",
				"Suresnes",
				"92150",
				"France",
				days,
				speecies,
				false,
				0.5,
				CaregiverTypes.GROOMER,
				false,
				0.0,
				0.0
		);
		var checkEquality = new CreateCaregiver(
				"Sid",
				"Bennaceur",
				"sa.bennaceur@test-db-test.com",
				"0764017528",
				"101, rue des Acquevilles",
				"Suresnes",
				"92150",
				"France",
				days,
				speecies,
				false,
				0.5,
				CaregiverTypes.GROOMER,
				false,
				0.0,
				0.0
		);
		Assertions.assertEquals(createCaregiver.toString(), checkEquality.toString());
		Assertions.assertEquals(createCaregiver, checkEquality);
	}
}
