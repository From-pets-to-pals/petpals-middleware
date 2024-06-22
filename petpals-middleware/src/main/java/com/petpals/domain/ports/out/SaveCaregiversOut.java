package com.petpals.domain.ports.out;

import com.petpals.domain.commands.caregivers.CreateCaregiverCommand;

public interface SaveCaregiversOut {
	String createCaregiver(CreateCaregiverCommand createCaregiver);
}
