package com.petpals.application.mappers.caregivers;

import com.petpals.application.dto.caregivers.AuthCaregiverRequest;
import com.petpals.application.dto.pals.AuthOwnerRequest;
import com.petpals.domain.commands.caregivers.AuthCaregiverCommand;
import com.petpals.domain.commands.pals.AuthOwnerCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface AuthCaregiverRequestMapper {
	@Mapping(target = "email", source = "email")
	@Mapping(target = "password", source = "password")
	AuthCaregiverCommand toCommand(AuthCaregiverRequest request);
}
