package com.petpals.services;

import com.petpals.domain.ports.in.CaregiversHealthCheckIn;
import com.petpals.domain.ports.out.CaregiversHealthCheckOut;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

@QuarkusTest
class CaregiversHealthCheckServiceTest {
	@InjectMock
	CaregiversHealthCheckOut caregiverHealthCheckOut;
	@Inject
	CaregiversHealthCheckIn caregiversHealthCheckIn;
	@Test
	void shouldReturnHelloMessage(){
		Mockito.when(caregiverHealthCheckOut.hello()).thenReturn("Hello RestEASY");
		var res = caregiversHealthCheckIn.hello();
		Assertions.assertEquals("Hello RestEASY", res);
	}
	
	@ParameterizedTest
	@CsvSource(
			value = {"Sid","Nono","Lucas"}
	)
	void shouldReturnHelloNameMessage(String name){
		var toReturn = "Hello ".concat(name);
		Mockito.when(caregiverHealthCheckOut.helloYou(name)).thenReturn(toReturn);
		var res = caregiversHealthCheckIn.helloYou(name);
		Assertions.assertEquals(toReturn, res);
	}
}
