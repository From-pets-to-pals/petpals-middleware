package com.petpals.resources;

import com.petpals.domain.ports.in.CaregiversHealthCheckIn;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SecurityResourceTest {
	static final String name = "something";
	@InjectMock
	CaregiversHealthCheckIn caregiversHealthCheckClient;
	@Test
	void shouldFailAsUnauthorizedWithoutApiKey() {
		Mockito.when(caregiversHealthCheckClient.hello()).thenReturn("Hello RESTEasy");
		
		given()
				.when().get("/hello")
				.then()
				.statusCode(401);
	}
	
	@Test
	void shoulldAcceptRequestWhenProvidingFullCredentialsOnSecureEntrypoint() {
		
		Mockito.when(caregiversHealthCheckClient.helloYou(name)).thenReturn("Hello "+ name);
		given()
				.header("API-KEY", "pals")
				.when().get("/hello/".concat(name))
				.then()
				.statusCode(200)
				.body(is(
						"Hello ".concat(name)
				));
	}
	
	
	@Test
	void shouldReturnHelloWhenProvidingCredentials() {
		Mockito.when(caregiversHealthCheckClient.helloYou("nono")).thenReturn("Hello ".concat("nono"));
		given()
				.header("API-KEY", "pals")
				.when().get("/hello/nono")
				.then()
				.statusCode(200)
				.body(is(
						"Hello ".concat("nono")
				));
	}
	
}
