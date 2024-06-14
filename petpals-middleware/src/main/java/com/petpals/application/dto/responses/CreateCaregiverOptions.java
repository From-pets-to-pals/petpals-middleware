package com.petpals.application.dto.responses;


import com.petpals.shared.model.dto.Country;
import com.petpals.shared.model.dto.Specie;

import java.util.List;

public record CreateCaregiverOptions(List<Country> countries, List<Specie> species) {
}
