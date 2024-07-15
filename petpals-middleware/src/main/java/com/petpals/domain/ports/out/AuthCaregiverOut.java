package com.petpals.domain.ports.out;

import com.petpals.domain.commands.caregivers.AuthCaregiverCommand;

public interface AuthCaregiverOut {
	String authCaregivers(AuthCaregiverCommand authCaregiverCommand);
}
