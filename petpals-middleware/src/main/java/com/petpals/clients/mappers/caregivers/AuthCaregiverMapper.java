package com.petpals.clients.mappers.caregivers;

import com.petpals.clients.dto.caregivers.AuthCaregiver;
import com.petpals.clients.dto.pals.AuthOwner;
import com.petpals.domain.commands.pals.AuthOwnerCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AuthCaregiverMapper {
	AuthCaregiver fromDomain(AuthOwnerCommand authOwnerCommand);
}
