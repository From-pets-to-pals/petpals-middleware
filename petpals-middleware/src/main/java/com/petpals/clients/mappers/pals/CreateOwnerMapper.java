package com.petpals.clients.mappers.pals;

import com.petpals.clients.dto.pals.NewOwner;
import com.petpals.domain.commands.pals.CreateOwnerCommand;
import org.mapstruct.Mapper;

@Mapper(uses = {
		AddPalMapper.class
}, componentModel = "cdi")
public interface CreateOwnerMapper {
	NewOwner fromDomain(CreateOwnerCommand createOwnerCommand);
}
