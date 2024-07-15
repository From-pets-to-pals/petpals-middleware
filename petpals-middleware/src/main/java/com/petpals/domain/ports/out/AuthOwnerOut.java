package com.petpals.domain.ports.out;

import com.petpals.domain.commands.pals.AuthOwnerCommand;

public interface AuthOwnerOut {
	String authOwners(AuthOwnerCommand authOwnerCommand);
}
