package com.petpals.resources;

import com.petpals.domain.ports.in.CaregiversHealthCheckIn;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class SecurityResourceTest {
	
	@ConfigProperty(name = "middleware.api.key")
	String middlewareApiKey;
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
	void shouldAcceptRequestWhenProvidingFullCredentialsOnSecureEntrypoint() {
		
		Mockito.when(caregiversHealthCheckClient.helloYou(name)).thenReturn("Hello "+ name);
		given()
				.header("API-KEY", middlewareApiKey)
				.when().get("/hello/".concat(name))
				.then()
				.statusCode(200)
				.body(is(
						"Hello ".concat(name)
				));
	}
	
	@Test
	void shouldSayHello() {
		
		Mockito.when(caregiversHealthCheckClient.hello()).thenReturn("Hello RestEASY");
		given()
				.header("API-KEY", middlewareApiKey)
				.when().get("/hello")
				.then()
				.statusCode(200)
				.body(is(
						"Hello RestEASY"
				));
	}
	@Test
	void shouldReturnHelloWhenProvidingCredentials() {
		Mockito.when(caregiversHealthCheckClient.helloYou("nono")).thenReturn("Hello ".concat("nono"));
		given()
				.header("API-KEY", middlewareApiKey)
				.when().get("/hello/nono")
				.then()
				.statusCode(200)
				.body(is(
						"Hello ".concat("nono")
				));
	}
	
}
