package com.petpals.domain.ports.in;


import com.petpals.shared.model.Breed;
import com.petpals.shared.model.Country;

import java.util.List;

public interface MenuOptionsIn {
	List<Country> getCountries();
	
	List<Breed> getBreeds();
}
