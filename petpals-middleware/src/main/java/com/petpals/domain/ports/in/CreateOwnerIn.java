package com.petpals.domain.ports.in;

import com.petpals.domain.commands.pals.CreateOwnerCommand;

public interface CreateOwnerIn {
	void createOwners(CreateOwnerCommand createOwnerCommand);
}
