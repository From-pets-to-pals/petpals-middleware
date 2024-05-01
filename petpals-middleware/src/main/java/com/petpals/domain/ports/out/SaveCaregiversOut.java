package com.petpals.domain.ports.out;

import com.petpals.clients.dto.caregivers.CreateCaregiver;

public interface SaveCaregiversOut {
	String createCaregiver(CreateCaregiver	createCaregiver);
}
