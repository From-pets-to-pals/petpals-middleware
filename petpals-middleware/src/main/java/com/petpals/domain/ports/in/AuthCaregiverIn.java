package com.petpals.domain.ports.in;

import com.petpals.domain.commands.caregivers.AuthCaregiverCommand;

public interface AuthCaregiverIn {
	String authCaregivers(AuthCaregiverCommand authCaregiverCommand);
}
