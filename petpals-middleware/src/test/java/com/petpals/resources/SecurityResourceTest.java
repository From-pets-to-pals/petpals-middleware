package com.petpals.resources;

import com.petpals.domain.ports.in.CaregiversHealthCheckIn;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
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
	void shouldAcceptRequestWhenEntrypointNotSecured() {
		Mockito.when(caregiversHealthCheckClient.hello()).thenReturn("Hello RESTEasy");
		
		given()
				.when().get("/hello")
				.then()
				.statusCode(200)
				.body(is("Hello RESTEasy"));
	}
	
	@Test
	void shouldThrowError400OnRestClientErrorForLogin() {
		Mockito.when(caregiversHealthCheckClient.hello()).thenThrow(ResteasyClientErrorException.class);
		
		given()
				.when().get("/hello")
				.then()
				.statusCode(400);
	}
	
	
	@Test
	void shoulldAcceptRequestWhenProvidingFullCredentialsOnSecureEntrypoint() {
		
		Mockito.when(caregiversHealthCheckClient.helloYou(name)).thenReturn("Hello "+ name);
		given()
				.header("API-KEY", "pals")
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.when().get("/hello/".concat(name))
				.then()
				.statusCode(200)
				.body(is(
						"Hello ".concat(name)
				));
	}
	
	@Test
	void shouldThrowError400OnRestClientErrorForCustomHelloMessage() {
		Mockito.when(caregiversHealthCheckClient.helloYou("zorro")).thenThrow(ResteasyClientErrorException.class);
		given()
				.header("API-KEY", "pals")
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.when().get("/hello/".concat("zorro"))
				.then()
				.statusCode(400);
	}
	
	@Test
	void shouldReturnHelloWithNameWhenProvidingCredentials() {
		Mockito.when(caregiversHealthCheckClient.helloYou("nono")).thenReturn("Hello ".concat("nono"));
		given()
				.header("API-KEY", "pals")
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.when().get("/hello/nono")
				.then()
				.statusCode(200)
				.body(is(
						"Hello ".concat("nono")
				));
	}
	
}
