package com.petpals.clients.menus;

import com.petpals.clients.endpoints.MenuOptionsClient;
import com.petpals.domain.ports.out.MenuOptionsOut;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import com.petpals.shared.model.dto.Country;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@QuarkusTest
class MenuOptionsClientTest {
	@Inject
	MenuOptionsOut menuOptionsOut;
	
	@InjectMock
	@RestClient
	MenuOptionsClient menuOptionsClient;
	
	@Test
	void shouldReturnEmptyList(){
		var toReturn = new ArrayList<>();
		Mockito.when(menuOptionsClient.getCountries()).thenReturn(new ArrayList<>());
		var res = menuOptionsOut.getCountries();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnOneCountryList(){
		var toReturn = List.of(new Country("FR", "FRANCE", "001"));
		Mockito.when(menuOptionsClient.getCountries()).thenReturn(toReturn);
		var res = menuOptionsOut.getCountries();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldThrowRestWebException(){
		Mockito.when(menuOptionsClient.getCountries()).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(
				PetPalsExceptions.class,
				() -> menuOptionsOut.getCountries());
		Mockito.verify(menuOptionsClient).getCountries();
	}
	
	@Test
	void shouldThrowRestClientException(){
		Mockito.when(menuOptionsClient.getCountries()).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> menuOptionsOut.getCountries());
		Mockito.verify(menuOptionsClient).getCountries();
	}}