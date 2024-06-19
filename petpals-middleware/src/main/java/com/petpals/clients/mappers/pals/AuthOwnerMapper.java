package com.petpals.clients.mappers.pals;

import com.petpals.clients.dto.pals.AuthOwner;
import com.petpals.clients.dto.pals.NewOwner;
import com.petpals.domain.commands.pals.AuthOwnerCommand;
import com.petpals.domain.commands.pals.CreateOwnerCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AuthOwnerMapper {
	AuthOwner fromDomain(AuthOwnerCommand authOwnerCommand);
}
