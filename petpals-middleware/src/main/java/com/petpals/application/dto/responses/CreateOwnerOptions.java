package com.petpals.application.dto.responses;


import com.petpals.shared.model.dto.BreedWithoutSpecie;
import com.petpals.shared.model.dto.Country;
import com.petpals.shared.model.dto.Specie;

import java.util.List;

public record CreateOwnerOptions(List<Country> countries, List<Specie> species, List<BreedWithoutSpecie> dogBreeds,
								 List<BreedWithoutSpecie> catBreeds, List<BreedWithoutSpecie> nacBreeds) {
}
