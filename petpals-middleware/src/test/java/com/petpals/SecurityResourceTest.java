package com.petpals;

import com.petpals.clients.services.CaregiverLoginService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SecurityResourceTest {
	static final String name = "sid";
	@InjectMock
	@RestClient
	CaregiverLoginService caregiverLoginService;
	@Test
	void shouldAcceptRequestWhenEntrypointNotSecured() {
		Mockito.when(caregiverLoginService.hello()).thenReturn("Hello RESTEasy");
		
		given()
				.when().get("/login")
				.then()
				.statusCode(200)
				.body(is("Hello RESTEasy"));
	}
	
	@Test
	void shouldThrowError400OnRestClientErrorForLogin() {
		Mockito.when(caregiverLoginService.hello()).thenThrow(ResteasyClientErrorException.class);
		
		given()
				.when().get("/login")
				.then()
				.statusCode(400);
	}
	
	@Test
	void shouldReturnUnauthorizedWhenEntrypointSecuredAndNoHeaders() {
		given()
				.when().get("/login/hello/".concat(name))
				.then()
				.statusCode(401);
	}
	
	@Test
	void shouldReturnUnauthorizedWhenEntrypointSecuredAndInvalidApiKeyHeader() {
		given()
				.header("API-KEY", "wrong")
				.when().get("/login/hello/".concat(name))
				.then()
				.statusCode(401);
	}
	
	@Test
	void shouldRejectRequestWhenEntrypointSecuredWithAuthorizationButNoApiKey() {
		given()
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.when().get("/login/hello/".concat(name))
				.then()
				.statusCode(401);
	}
	
	@Test
	void shoulldAcceptRequestWhenProvidingFullCredentialsOnSecureEntrypoint() {
		
		Mockito.when(caregiverLoginService.helloYou(name)).thenReturn("Hello "+ name);
		given()
				.header("API-KEY", "pals")
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.when().get("/login/hello/".concat(name))
				.then()
				.statusCode(200)
				.body(is(
						"Hello ".concat(name)
				));
	}
	
	
	@Test
	void testtt() {
		Mockito.when(caregiverLoginService.helloYou(name)).thenReturn("Hello ".concat(name));
		given()
				.header("API-KEY", "pals")
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.when().get("/login/hello/".concat(name))
				.then()
				.statusCode(200)
				.body(is(
						"Hello ".concat(name)
				));
	}
	
	@Test
	void shouldThrowError400OnRestClientErrorForCustomHelloMessage() {
		Mockito.when(caregiverLoginService.helloYou(name)).thenThrow(ResteasyClientErrorException.class);
		given()
				.header("API-KEY", "pals")
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxNTEzMzgyMCwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzkyNDIyMCwianRpIjoiMzA3ZTY3Y2QtZDQ1Zi00OWMyLWFlZTEtZmZiNTI5MWZmOWVkIn0.qnQ1znsWaqfO-mU0LLA5EwSJ1L8Ko01-Qx5lF5WeZUkwk_nmh0arO16CtsJmmwi-pFrbipnKmlp9z9sLevoIrqb9ldQ7DQPDgbF0QCXQjGJK9BQjqieIyw9lLXgLlwn-VZv-tG74JvPnUxXOZpWit1MPBSwuWqfHwBe_McBV9pBKOwZ33Gx_c2SGjjUCel1ChCmAx0VXkEdivm-tcAzeOmPyFcphMdNB22CyiqrETtegfKH3eBAa81n4s0kFOjVJ-B6uFIQKzOwHnvKbg7OdxHDlXSVIoUaE4e1WEv2uR2NZTSVlrP9KIO24zg6TKMdf1vKfIod77AAASfmo21cBVA")
				.when().get("/login/hello/".concat(name))
				.then()
				.statusCode(400);
	}
}
