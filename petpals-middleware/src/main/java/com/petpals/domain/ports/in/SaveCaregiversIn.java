package com.petpals.domain.ports.in;

import com.petpals.clients.dto.caregivers.CreateCaregiver;

public interface SaveCaregiversIn {
	String createCaregiver(CreateCaregiver	createCaregiver);
}
