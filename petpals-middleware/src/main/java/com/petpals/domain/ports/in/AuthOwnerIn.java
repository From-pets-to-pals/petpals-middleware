package com.petpals.domain.ports.in;

import com.petpals.domain.commands.pals.AuthOwnerCommand;

public interface AuthOwnerIn {
	String authOwners(AuthOwnerCommand authOwnerCommand);
}
