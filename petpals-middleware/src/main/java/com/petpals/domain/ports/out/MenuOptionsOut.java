package com.petpals.domain.ports.out;


import com.petpals.shared.model.Breed;
import com.petpals.shared.model.BreedWithoutSpecie;
import com.petpals.shared.model.Country;
import com.petpals.shared.model.Specie;

import java.util.List;

public interface MenuOptionsOut {
	List<Country> getCountries();
	List<Breed> getBreeds();
	
	List<BreedWithoutSpecie> getDogBreeds();
	
	List<BreedWithoutSpecie> getCatBreeds();
	
	List<BreedWithoutSpecie> getNacBreeds();
	List<Specie> getSpecies();
}
