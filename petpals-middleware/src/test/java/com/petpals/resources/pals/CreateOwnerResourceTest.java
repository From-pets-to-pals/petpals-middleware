package com.petpals.resources.pals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.application.dto.pals.CreateOwnerRequest;
import com.petpals.application.dto.pals.CreatePalRequest;
import com.petpals.application.mappers.pals.CreateOwnerRequestMapper;
import com.petpals.domain.ports.in.CreateOwnerIn;
import com.petpals.shared.model.dto.PalIdentityInformation;
import com.petpals.shared.model.dto.PalMeasurement;
import com.petpals.shared.model.dto.PalMedicalInformation;
import com.petpals.shared.model.enums.SpeciesEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class CreateOwnerResourceTest {
	@Inject
	CreateOwnerRequestMapper createOwnerRequestMapper;
	@ConfigProperty(name = "middleware.api.key")
	String apiKey;
	@InjectMock
	CreateOwnerIn createOwnerIn;
	CreateOwnerRequest createOwnerRequest;
	
	@BeforeEach
	void setUp() {
		createOwnerRequest = new CreateOwnerRequest(
				"sa.bennaceur@gmail.com",
				"OPPO X59",
				"0764017528",
				"FRANCE",
				List.of(new CreatePalRequest(
								new PalMedicalInformation(
										true,
										new ArrayList<>(),
										null,
										null,
										false
								),
								
								new PalIdentityInformation(
										"Ashe",
										"Ashe",
										"2022-03-28",
										true,
										SpeciesEnum.DOG,
										"Husky",
										"250261245784512",
										true
								),
								new PalMeasurement(
								29.0,
								53.5
							)
						)
				)
		);
		var ownerCommand = createOwnerRequestMapper.toCommand(createOwnerRequest);
		Mockito.when(createOwnerIn.createOwners(ownerCommand)).thenReturn("123456789456123");
	}
	
	@Test
	void testCreateCaregiverOkRequest() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString(createOwnerRequest);
		given()
				.headers("API-KEY", apiKey)
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.header("Content-Type", "application/json")
				.body(json)
				.when().post("/owners")
				.then()
				.statusCode(200)
				.body(is("123456789456123"));
	}
	
	@Test
	void testCreateCaregiverBadRequest() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		CreateOwnerBadRequest badRequest = new CreateOwnerBadRequest(
				12,
				"sa.bennaceur@gmail.com",
				"OPPOx59",
				"Sidou",
				"PARIS_FR",
				new ArrayList<>()
		);
		var json = mapper.writeValueAsString(badRequest);
		given()
				.headers("API-KEY", apiKey)
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.header("Content-Type", "application/json")
				.body(json)
				.when().post("/owners")
				.then()
				.statusCode(400);
	}
	
	@Test
	void testCreateCaregiverInvalidApiKey() throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString(createOwnerRequest);
		given()
				.headers("API-KEY", "wrong")
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.header("Content-Type", "application/json")
				.body(json)
				.when().post("/owners")
				.then()
				.statusCode(401);
	}
	
	@Test
	void testCreateCaregiverUnauthorizedNoApiKey() throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		var json = mapper.writeValueAsString(createOwnerRequest);
		given()
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.header("Content-Type", "application/json")
				.body(json)
				.when().post("/owners")
				.then()
				.statusCode(401);
	}
	
	public record CreateOwnerBadRequest (int illegalProperty, @Email String email, @NotBlank String deviceId,
										 @NotBlank String username,
										 @NotBlank String location, @NotNull List<CreatePalRequest> pals) {
		
		
	}
}
