package com.petpals.resources;

import com.petpals.domain.ports.in.JwtTokenGeneratorPort;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class JwtTokenResourceTest {
	static final String email = "sa.bennaceur@test.com";
	static final String caregiverType = "Groomers";
	static final String response = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9" +
										   ".eyJpc3MiOiJwZXRwYWxzIiwidXBuIjoic2EuYmVubmFjZXVyQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJDYXJlZ2l2ZXJzIiwiT3duZXJzIl0sImV4cCI6MTcxMzgzMTI0MSwiYWRkcmVzcyI6InBldHBhbHMtYXBwcyIsImlhdCI6MTcxMzgzMDY0MSwianRpIjoiZTczNTQ5ODctYmVmOC00NjQ4LTlkN2UtNjUyNjI3ZWU1MmNhIn0.NRtM3lMKw6OD5J8aL_K5VjZltMs7nvaT1aClRDj-oESmGRQxl_hLzJ-X5_PhNadKsPuyWw_x013uQQzm8JlsAifRKeGnNEFLRPgXVDBwMcUeBE25E6Zq4_-h4fJOYiAm9jvl8DtBWa5si381z_zCqVjqX6NnnLNHje94lw9fvBdM9lzOculodngeFUEhTzfe1LGDs-AVREdAhOUhTMdi78WGLSXawXy9IyGCL5qWGmnFhCrgvxXivIwD5NUPB40s03a3k_iQXe-2hXlZVAZ_IvsOP_RUwZyuqQKsqVW5_tO8bI0QjVwOmmJUggYxwAifiKjYU7uaQ8qOaT1Bgt5iCA";
	@InjectMock
	JwtTokenGeneratorPort jwtTokenGeneratorPort;
	@Test
	void shouldNotGetTokeWithoutApiKey() {
		Mockito.when(jwtTokenGeneratorPort.getToken(email,caregiverType)).thenReturn(response);
		
		given()
				.when().get(String.format("/token/%s/%s", email, caregiverType))
				.then()
				.statusCode(401);
	}
}
