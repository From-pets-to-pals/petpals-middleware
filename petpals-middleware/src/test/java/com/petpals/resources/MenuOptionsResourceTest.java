package com.petpals.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.application.dto.responses.CreateCaregiverOptions;
import com.petpals.application.dto.responses.CreateOwnerOptions;
import com.petpals.domain.ports.in.MenuOptionsIn;
import com.petpals.shared.model.dto.BreedWithoutSpecie;
import com.petpals.shared.model.dto.Country;
import com.petpals.shared.model.dto.Specie;
import com.petpals.shared.model.enums.SpeciesEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class MenuOptionsResourceTest {
	static List<Specie> specieList = List.of(new Specie(SpeciesEnum.DOG.name()), new Specie(SpeciesEnum.CAT.name()),
											 new Specie(SpeciesEnum.NAC.name()));
	
	static List<Country> countryList = List.of(new Country("FR","France","250"));
	static List<BreedWithoutSpecie> dogBreeds = List.of(new BreedWithoutSpecie("Husky"));
	
	static List<BreedWithoutSpecie> catBreeds = List.of(new BreedWithoutSpecie("Siamois"));
	
	static List<BreedWithoutSpecie> nacBreeds = List.of();
	
	
	static ObjectMapper mapper = new ObjectMapper();
	@ConfigProperty(name ="middleware.api.key")
	String apiKey;
	@InjectMock
	MenuOptionsIn menuOptionsIn;
	
	@Test
	void shouldGetCaregiverMenuOptions() throws JsonProcessingException {
		Mockito.when(menuOptionsIn.getSpecies()).thenReturn(specieList);
		Mockito.when(menuOptionsIn.getCountries()).thenReturn(countryList);
		CreateCaregiverOptions options = new CreateCaregiverOptions(countryList, specieList);
		var json = mapper.writeValueAsString(options);
		given()
				.headers("API-KEY", apiKey)
				.header("Content-Type", "application/json")
				.when().get("/options/create/caregiver")
				.then()
				.statusCode(200)
				.body(is(json));
	}
	
	@Test
	void shouldGetOwnerMenuOptions() throws JsonProcessingException {
		Mockito.when(menuOptionsIn.getSpecies()).thenReturn(specieList);
		Mockito.when(menuOptionsIn.getCountries()).thenReturn(countryList);
		Mockito.when(menuOptionsIn.getDogBreeds()).thenReturn(dogBreeds);
		Mockito.when(menuOptionsIn.getCatBreeds()).thenReturn(catBreeds);
		Mockito.when(menuOptionsIn.getNacBreeds()).thenReturn(nacBreeds);
		
		CreateOwnerOptions options = new CreateOwnerOptions(countryList, specieList, dogBreeds, catBreeds, nacBreeds);
		var json = mapper.writeValueAsString(options);
		given()
				.headers("API-KEY", apiKey)
				.header("Content-Type", "application/json")
				.when().get("/options/create/owner")
				.then()
				.statusCode(200)
				.body(is(json));
	}
}
