package com.petpals.application.dto.responses;

import com.petpals.clients.dto.caregivers.CreateCaregiver;
import com.petpals.domain.commands.caregivers.CreateCaregiverCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface CreateCaregiverCommandMapper {
	@Mapping(source = "isSubscribed", target = "isSubscribed")
	CreateCaregiverCommand toDomain(CreateCaregiver caregiver);
}
