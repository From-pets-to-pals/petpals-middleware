package com.petpals.domain.ports.out;

import com.petpals.domain.commands.caregivers.CreateCaregiverCommand;

public interface SaveCaregiversOut {
	void createCaregiver(CreateCaregiverCommand createCaregiver);
}
