package com.petpals.domain.ports.in;



import com.petpals.shared.model.dto.Breed;
import com.petpals.shared.model.dto.BreedWithoutSpecie;
import com.petpals.shared.model.dto.Country;
import com.petpals.shared.model.dto.Specie;

import java.util.List;

public interface MenuOptionsIn {
	List<Country> getCountries();
	
	List<Breed> getBreeds();
	List<BreedWithoutSpecie> getDogBreeds();
	List<BreedWithoutSpecie> getCatBreeds();
	List<BreedWithoutSpecie> getNacBreeds();
	
	List<Specie> getSpecies();
}
