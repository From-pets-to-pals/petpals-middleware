package com.petpals.application.mappers.caregivers;

import com.petpals.application.dto.caregivers.CreateCaregiverRequest;
import com.petpals.application.dto.pals.CreateOwnerRequest;
import com.petpals.application.mappers.pals.CreatePalsRequestMapper;
import com.petpals.domain.commands.caregivers.CreateCaregiverCommand;
import com.petpals.domain.commands.pals.CreateOwnerCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface CreateCaregiverRequestMapper {
	@Mapping(target = "reference", ignore = true)
	@Mapping(target = "firstName", source = "firstName")
	@Mapping(target = "lastName", source = "lastName")
	@Mapping(target = "password", source = "password")
	CreateCaregiverCommand toCommand(CreateCaregiverRequest request);
}
