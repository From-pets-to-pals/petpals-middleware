package com.petpals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.clients.dto.CreateCaregiver;
import com.petpals.clients.dto.Days;
import com.petpals.clients.services.CaregiverLoginService;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
class ConnectionResourceTest {
	
	@BeforeEach
	public void setUp() {
		//when(mock.hello()).thenReturn("Hello RESTEasy");
	}
	
	
	
	
	@Test
	void testCreateCaregiverBadRequest() throws JsonProcessingException {
		var createCaregiver = new CreateCaregiver(
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
