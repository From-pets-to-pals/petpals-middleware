package com.petpals.application.mappers.pals;

import com.petpals.application.dto.CreateOwnerRequest;
import com.petpals.domain.commands.pals.CreateOwnerCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", uses = {CreatePalsRequestMapper.class})
public interface CreateOwnerRequestMapper {
	@Mapping(target = "reference", ignore = true)
	CreateOwnerCommand toCommand(CreateOwnerRequest request);
}
