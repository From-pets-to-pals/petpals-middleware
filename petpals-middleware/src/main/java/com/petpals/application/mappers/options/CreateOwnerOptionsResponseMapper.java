package com.petpals.application.mappers.options;

import com.petpals.application.dto.responses.CreateOwnerOptions;
import com.petpals.shared.model.dto.BreedWithoutSpecie;
import com.petpals.shared.model.dto.Country;
import com.petpals.shared.model.dto.Specie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CreateOwnerOptionsResponseMapper {
	CreateOwnerOptions toResponse(List<Country> countries, List<Specie> species, List<BreedWithoutSpecie> dogBreeds,
								  List<BreedWithoutSpecie> catBreeds, List<BreedWithoutSpecie> nacBreeds);
}
