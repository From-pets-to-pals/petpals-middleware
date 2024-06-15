package com.petpals.clients.menus;

import com.petpals.clients.endpoints.MenuOptionsClient;
import com.petpals.domain.ports.out.MenuOptionsOut;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import com.petpals.shared.model.dto.Breed;
import com.petpals.shared.model.dto.BreedWithoutSpecie;
import com.petpals.shared.model.dto.Country;
import com.petpals.shared.model.dto.Specie;
import com.petpals.shared.model.enums.SpeciesEnum;
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
	void shouldReturnEmptyCountryList(){
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
	void shouldReturnEmptySpecieList(){
		var toReturn = new ArrayList<>();
		Mockito.when(menuOptionsClient.getSpecies()).thenReturn(new ArrayList<>());
		var res = menuOptionsOut.getSpecies();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnOneSpecieList(){
		var toReturn = List.of(new Specie(SpeciesEnum.DOG.name()));
		Mockito.when(menuOptionsClient.getSpecies()).thenReturn(toReturn);
		var res = menuOptionsOut.getCountries();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnEmptyBreedList(){
		var toReturn = new ArrayList<>();
		Mockito.when(menuOptionsClient.getBreeds()).thenReturn(new ArrayList<>());
		var res = menuOptionsOut.getBreeds();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnOneBreedList(){
		var toReturn = List.of(new Breed("Husky",new Specie(SpeciesEnum.DOG.name())));
		Mockito.when(menuOptionsClient.getBreeds()).thenReturn(toReturn);
		var res = menuOptionsOut.getBreeds();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnEmptyDogBreedList(){
		var toReturn = new ArrayList<>();
		Mockito.when(menuOptionsClient.getDogBreeds()).thenReturn(new ArrayList<>());
		var res = menuOptionsOut.getDogBreeds();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnOneDogBreedList(){
		var toReturn = List.of(new BreedWithoutSpecie("Husky"));
		Mockito.when(menuOptionsClient.getDogBreeds()).thenReturn(toReturn);
		var res = menuOptionsOut.getDogBreeds();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnEmptyCatBreedList(){
		var toReturn = new ArrayList<>();
		Mockito.when(menuOptionsClient.getCatBreeds()).thenReturn(new ArrayList<>());
		var res = menuOptionsOut.getCatBreeds();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnOneCatBreedList(){
		var toReturn = List.of(new BreedWithoutSpecie("Birman"));
		Mockito.when(menuOptionsClient.getCatBreeds()).thenReturn(toReturn);
		var res = menuOptionsOut.getCatBreeds();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldReturnEmptyNacBreedList(){
		var toReturn = new ArrayList<>();
		Mockito.when(menuOptionsClient.getNacBreeds()).thenReturn(new ArrayList<>());
		var res = menuOptionsOut.getNacBreeds();
		Assertions.assertEquals(toReturn, res);
	}
	
	@Test
	void shouldThrowRestWebExceptionOnGetCountries(){
		Mockito.when(menuOptionsClient.getCountries()).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(
				PetPalsExceptions.class,
				() -> menuOptionsOut.getCountries());
		Mockito.verify(menuOptionsClient).getCountries();
	}
	
	@Test
	void shouldThrowRestClientExceptionOnGetCountries(){
		Mockito.when(menuOptionsClient.getCountries()).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> menuOptionsOut.getCountries());
		Mockito.verify(menuOptionsClient).getCountries();
	}
	
	@Test
	void shouldThrowRestWebExceptionOnGetBreeds(){
		Mockito.when(menuOptionsClient.getBreeds()).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(
				PetPalsExceptions.class,
				() -> menuOptionsOut.getBreeds());
		Mockito.verify(menuOptionsClient).getBreeds();
	}
	
	@Test
	void shouldThrowRestClientExceptionOnGetBreeds(){
		Mockito.when(menuOptionsClient.getBreeds()).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> menuOptionsOut.getBreeds());
		Mockito.verify(menuOptionsClient).getBreeds();
	}
	
	@Test
	void shouldThrowRestWebExceptionOnGetSpecies(){
		Mockito.when(menuOptionsClient.getSpecies()).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(
				PetPalsExceptions.class,
				() -> menuOptionsOut.getSpecies());
		Mockito.verify(menuOptionsClient).getSpecies();
	}
	
	@Test
	void shouldThrowRestClientExceptionOnGetDogBreeds(){
		Mockito.when(menuOptionsClient.getDogBreeds()).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> menuOptionsOut.getDogBreeds());
		Mockito.verify(menuOptionsClient).getDogBreeds();
	}
	
	@Test
	void shouldThrowRestWebExceptionOnGetDogBreeds(){
		Mockito.when(menuOptionsClient.getDogBreeds()).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(
				PetPalsExceptions.class,
				() -> menuOptionsOut.getDogBreeds());
		Mockito.verify(menuOptionsClient).getDogBreeds();
	}
	
	@Test
	void shouldThrowRestClientExceptionOnGetCatBreeds(){
		Mockito.when(menuOptionsClient.getCatBreeds()).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> menuOptionsOut.getCatBreeds());
		Mockito.verify(menuOptionsClient).getCatBreeds();
	}
	@Test
	void shouldThrowRestWebExceptionOnGetCatBreeds(){
		Mockito.when(menuOptionsClient.getCatBreeds()).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(
				PetPalsExceptions.class,
				() -> menuOptionsOut.getCatBreeds());
		Mockito.verify(menuOptionsClient).getCatBreeds();
	}
	
	@Test
	void shouldThrowRestClientExceptionOnGetNacBreeds(){
		Mockito.when(menuOptionsClient.getNacBreeds()).thenThrow(ResteasyClientErrorException.class);
		Assertions.assertThrows(PetPalsExceptions.class, () -> menuOptionsOut.getNacBreeds());
		Mockito.verify(menuOptionsClient).getNacBreeds();
	}
	@Test
	void shouldThrowRestWebExceptionOnGetNacBreeds(){
		Mockito.when(menuOptionsClient.getNacBreeds()).thenThrow(ResteasyWebApplicationException.class);
		Assertions.assertThrows(
				PetPalsExceptions.class,
				() -> menuOptionsOut.getNacBreeds());
		Mockito.verify(menuOptionsClient).getNacBreeds();
	}
}