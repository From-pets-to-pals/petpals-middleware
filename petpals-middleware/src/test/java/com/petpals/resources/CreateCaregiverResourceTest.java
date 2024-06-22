package com.petpals.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.clients.dto.caregivers.Days;
import com.petpals.domain.commands.caregivers.CreateCaregiverCommand;
import com.petpals.domain.ports.in.SaveCaregiversIn;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.model.enums.PalsFriendsTypes;
import com.petpals.shared.model.enums.SpeciesEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;

@QuarkusTest
class CreateCaregiverResourceTest {
	@ConfigProperty(name ="middleware.api.key")
	String apiKey;
	@InjectMock
	SaveCaregiversIn saveCaregiversIn;
	static CreateCaregiverCommand createCaregiver;
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
		Mockito.when(saveCaregiversIn.createCaregiver(Mockito.any(CreateCaregiverCommand.class))).thenReturn("test");
	}
	
	@Test
	void testCreateCaregiverBadRequest() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString(createCaregiver);
		given()
				.headers("API-KEY", apiKey)
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.header("Content-Type", "application/json")
				.body(json)
				.when().post("/caregivers")
				.then()
				.statusCode(400);
	}
	
	@Test
	void testCreateCaregiverInvalidApiKey() throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString(createCaregiver);
		given()
				.headers("API-KEY", "wrong")
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.header("Content-Type", "application/json")
				.body(json)
				.when().post("/caregivers")
				.then()
				.statusCode(401);
	}
	
	@Test
	void testCreateCaregiverUnauthorizedNoApiKey() throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString(createCaregiver);
		given()
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.header("Content-Type", "application/json")
				.body(json)
				.when().post("/caregivers")
				.then()
				.statusCode(401);
	}
}
