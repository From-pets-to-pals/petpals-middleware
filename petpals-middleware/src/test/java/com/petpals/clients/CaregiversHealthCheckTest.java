package com.petpals.clients;

import com.petpals.clients.endpoints.CaregiversHealthCheckClient;
import com.petpals.domain.ports.out.CaregiversHealthCheckOut;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

@QuarkusTest
class CaregiversHealthCheckTest {
	
	@InjectMock
	@RestClient
	CaregiversHealthCheckClient caregiversHealthCheckClient;
	@Inject
	CaregiversHealthCheckOut   caregiversHealthCheckOut;
	
	@Test
	void shouldReturnHelloMessage(){
		Mockito.when(caregiversHealthCheckClient.hello()).thenReturn("Hello RestEASY");
		var res = caregiversHealthCheckOut.hello();
		Assertions.assertEquals("Hello RestEASY", res);
	}
	
	@ParameterizedTest
	@CsvSource(
			value = {"Sid","Nono","Lucas"}
	)
	void shouldReturnHelloNameMessage(String name){
		var toReturn = "Hello ".concat(name);
		Mockito.when(caregiversHealthCheckClient.helloYou(name)).thenReturn(toReturn);
		var res = caregiversHealthCheckOut.helloYou(name);
		Assertions.assertEquals(toReturn, res);
	}
}
