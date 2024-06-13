package com.petpals.application.dto.responses;

import com.petpals.shared.model.Breed;
import com.petpals.shared.model.Country;
import com.petpals.shared.model.Specie;

import java.util.List;

public record CreateOwnerOptions(List<Country> countries, List<Specie> species, List<Breed> breeds) {
}
