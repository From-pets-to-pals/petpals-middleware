package com.petpals.application.mappers.options;

import com.petpals.application.dto.responses.CreateCaregiverOptions;
import com.petpals.shared.model.Country;
import com.petpals.shared.model.Specie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CreateCaregiverOptionsResponseMapper {
	CreateCaregiverOptions toResponse(List<Country> countries, List<Specie> species);
}
