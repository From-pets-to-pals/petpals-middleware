package com.petpals.application.mappers;

import com.petpals.application.dto.CreatePalRequest;
import com.petpals.domain.commands.pals.CreatePalCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface CreatePalsRequestMapper {
	@Mapping(target = "reference", ignore = true)
	CreatePalCommand toCommand(CreatePalRequest request);
}
