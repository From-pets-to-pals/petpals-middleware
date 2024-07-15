package com.petpals.application.mappers.pals;

import com.petpals.application.dto.caregivers.CreateCaregiverRequest;
import com.petpals.application.dto.pals.CreatePalRequest;
import com.petpals.application.mappers.caregivers.CreateCaregiverRequestMapper;
import com.petpals.domain.commands.caregivers.CreateCaregiverCommand;
import com.petpals.domain.commands.pals.CreatePalCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface CreatePalsRequestMapper {
	CreateCaregiverCommand toCommand(CreateCaregiverRequest request);
}
