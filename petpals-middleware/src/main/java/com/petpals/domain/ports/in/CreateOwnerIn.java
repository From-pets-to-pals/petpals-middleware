package com.petpals.domain.ports.in;

import com.petpals.domain.commands.pals.CreateOwnerCommand;

public interface CreateOwnerIn {
	String createOwners(CreateOwnerCommand createOwnerCommand);
}
