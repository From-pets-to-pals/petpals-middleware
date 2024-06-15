package com.petpals.services;

import com.petpals.domain.ports.in.MenuOptionsIn;
import com.petpals.domain.ports.out.MenuOptionsOut;
import com.petpals.shared.model.dto.Country;
import com.petpals.shared.model.dto.Specie;
import com.petpals.shared.model.enums.SpeciesEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@QuarkusTest
class MenuOptionsServiceTest {
	@Inject
	MenuOptionsIn menuOptionsIn;
	@InjectMock
	MenuOptionsOut menuOptionsOut;
	
	@Test
	void testGetCountries() {
		var france = new Country("Fr","France","250");
		List<Country> countries = List.of(france);
		Mockito.when(menuOptionsOut.getCountries()).thenReturn(countries);
		var fromRepository  = menuOptionsIn.getCountries();
		Assertions.assertEquals(1, fromRepository.size());
		Assertions.assertEquals(france.name(), fromRepository.get(0).name());
		Assertions.assertEquals(france.code(), fromRepository.get(0).code());
		Assertions.assertEquals(france.number(), fromRepository.get(0).number());
		Mockito.verify(menuOptionsOut).getCountries();
		Mockito.verifyNoMoreInteractions(menuOptionsOut);
	}
	
	@Test
	void testGetSpecies() {
		var specie = new Specie(SpeciesEnum.DOG.name());
		List<Specie> species = List.of(specie);
		Mockito.when(menuOptionsOut.getSpecies()).thenReturn(species);
		var fromRepository  = menuOptionsIn.getSpecies();
		Assertions.assertEquals(1, fromRepository.size());
		Assertions.assertEquals(specie.name(), SpeciesEnum.DOG.name());
		Mockito.verify(menuOptionsOut).getSpecies();
		Mockito.verifyNoMoreInteractions(menuOptionsOut);
	}
	
	@Test
	void testGetBreeds() {
		Mockito.when(menuOptionsOut.getBreeds()).thenReturn(new ArrayList<>());
		var fromRepository  = menuOptionsIn.getBreeds();
		Assertions.assertEquals(0, fromRepository.size());
		Mockito.verify(menuOptionsOut).getBreeds();
		Mockito.verifyNoMoreInteractions(menuOptionsOut);
	}
	
	@Test
	void testGetDogBreeds() {
		Mockito.when(menuOptionsOut.getDogBreeds()).thenReturn(new ArrayList<>());
		var fromRepository  = menuOptionsIn.getDogBreeds();
		Assertions.assertEquals(0, fromRepository.size());
		Mockito.verify(menuOptionsOut).getDogBreeds();
		Mockito.verifyNoMoreInteractions(menuOptionsOut);
	}
	
	@Test
	void testGetCatBreeds() {
		Mockito.when(menuOptionsOut.getCatBreeds()).thenReturn(new ArrayList<>());
		var fromRepository  = menuOptionsIn.getCatBreeds();
		Assertions.assertEquals(0, fromRepository.size());
		Mockito.verify(menuOptionsOut).getCatBreeds();
		Mockito.verifyNoMoreInteractions(menuOptionsOut);
	}
	
	@Test
	void testGetNacBreeds() {
		Mockito.when(menuOptionsOut.getNacBreeds()).thenReturn(new ArrayList<>());
		var fromRepository  = menuOptionsIn.getNacBreeds();
		Assertions.assertEquals(0, fromRepository.size());
		Mockito.verify(menuOptionsOut).getNacBreeds();
		Mockito.verifyNoMoreInteractions(menuOptionsOut);
	}
}
