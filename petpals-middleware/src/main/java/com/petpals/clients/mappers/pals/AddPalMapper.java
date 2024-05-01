package com.petpals.clients.mappers.pals;

import com.petpals.clients.dto.pals.AddFirstPal;
import com.petpals.domain.commands.pals.CreatePalCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AddPalMapper {
	AddFirstPal fromDomain(CreatePalCommand createPalCommand);
}
