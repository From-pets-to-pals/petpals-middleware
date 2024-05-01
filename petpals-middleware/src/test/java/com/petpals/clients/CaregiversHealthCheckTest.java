package com.petpals.clients;

import com.petpals.clients.endpoints.caregivers.CaregiversHealthCheckClient;
import com.petpals.domain.ports.out.CaregiversHealthCheckOut;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class CaregiversHealthCheckTest {
	
	@InjectMock
	@RestClient
	CaregiversHealthCheckClient caregiversHealthCheckClient;
	@Inject
	CaregiversHealthCheckOut   caregiversHealthCheckOut;
	
	@Order(1)
	@Test
	void shouldThrowResteasyClientErrorExceptionWhenCallingHello(){
	
		Mockito.when(caregiversHealthCheckClient.hello()).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversHealthCheckOut.hello());
		Mockito.verify(caregiversHealthCheckClient).hello();
	}
	
	
	@Order(2)
	@Test
	void shouldThrowResteasyWebApplicationExceptionWhenCallingHello(){
		Mockito.when(caregiversHealthCheckClient.hello()).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversHealthCheckOut.hello());
		Mockito.verify(caregiversHealthCheckClient).hello();
	}
	
	@Order(3)
	@Test
	void shouldReturnHelloMessage(){
		Mockito.when(caregiversHealthCheckClient.hello()).thenReturn("Hello RestEASY");
		var res = caregiversHealthCheckOut.hello();
		Assertions.assertEquals("Hello RestEASY", res);
	}
	@Order(4)
	@ParameterizedTest
	@CsvSource(
			value = {"Arnaud","Benjamin","David"}
	)
	void shouldThrowResteasyWeebApplicationExceptionWhenCallingHelloName(String name){
		Mockito.when(caregiversHealthCheckClient.helloYou(name)).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversHealthCheckOut.helloYou(name));
		Mockito.verify(caregiversHealthCheckClient).helloYou(name);
	}
	
	
	@Order(5)
	@ParameterizedTest
	@CsvSource(
			value = {"Shan","Denice","Denis"}
	)
	void shouldThrowResteasyClientApplicationExceptionWhenCallingHelloName(String name){
		Mockito.when(caregiversHealthCheckClient.helloYou(name)).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversHealthCheckOut.helloYou(name));
		Mockito.verify(caregiversHealthCheckClient).helloYou(name);
	}
	@Order(6)
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
