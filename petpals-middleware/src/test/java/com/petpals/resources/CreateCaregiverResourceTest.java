package com.petpals.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.clients.dto.Days;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;

@QuarkusTest
class CreateCaregiverResourceTest {
	@InjectMock
	SaveCaregiversIn saveCaregiversIn;
	static CreateCaregiver createCaregiver;
	@BeforeEach
	void setUp(){
		createCaregiver = new CreateCaregiver(
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
		Mockito.when(saveCaregiversIn.createCaregiver(Mockito.any(CreateCaregiver.class))).thenThrow(ResteasyClientErrorException.class);
	}
	
	@Test
	void testCreateCaregiverBadRequest() throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString(createCaregiver);
		given()
				.header("Content-Type", "application/json")
				.body(json)
				.when().post("/caregivers")
				.then()
				.statusCode(400);
	}
	
}
