package com.petpals.domain.ports.out;

import com.petpals.domain.commands.CreateCaregiverCommand;

public interface SaveCaregiversOut {
	String createCaregiver(CreateCaregiverCommand createCaregiver);
}
