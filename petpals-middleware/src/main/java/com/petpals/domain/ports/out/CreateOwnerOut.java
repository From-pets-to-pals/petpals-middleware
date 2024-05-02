package com.petpals.domain.ports.out;

import com.petpals.domain.commands.pals.CreateOwnerCommand;

public interface CreateOwnerOut {
	Long createOwners(CreateOwnerCommand createOwnerCommand);
}
