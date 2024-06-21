package com.petpals.domain.ports.in;

import com.petpals.domain.commands.CreateCaregiverCommand;

public interface SaveCaregiversIn {
	String createCaregiver(CreateCaregiverCommand createCaregiver);
}
