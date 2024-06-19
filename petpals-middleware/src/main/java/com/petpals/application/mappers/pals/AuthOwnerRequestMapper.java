package com.petpals.application.mappers.pals;

import com.petpals.application.dto.pals.AuthOwnerRequest;
import com.petpals.application.dto.pals.CreateOwnerRequest;
import com.petpals.domain.commands.pals.AuthOwnerCommand;
import com.petpals.domain.commands.pals.CreateOwnerCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface AuthOwnerRequestMapper {
	@Mapping(target = "email", source = "email")
	@Mapping(target = "password", source = "password")
	AuthOwnerCommand toCommand(AuthOwnerRequest request);
}
