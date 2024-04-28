package com.petpals.clients;

import com.petpals.clients.endpoints.CaregiversHealthCheckClient;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CaregiverHealthCheckClientTest {
	
	@Inject
	@RestClient
	CaregiversHealthCheckClient caregiversHealthCheckClient;
	
	
	@Test
	void testHealthCheck() {
		Assertions.assertEquals("Hello RESTEasy", caregiversHealthCheckClient.hello());
	}
	@Test
	void testHealthCheckName() {
		Assertions.assertEquals("Hello Sid", caregiversHealthCheckClient.helloYou("Sid"));
	}
}
