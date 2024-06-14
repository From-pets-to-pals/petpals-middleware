package com.petpals.application.dto.pals;


import com.petpals.shared.model.dto.PalIdentityInformation;
import com.petpals.shared.model.dto.PalMeasurement;
import com.petpals.shared.model.dto.PalMedicalInformation;
import jakarta.validation.constraints.NotNull;

public record CreatePalRequest(
		@NotNull PalMedicalInformation palMedicalInformation,
		@NotNull PalIdentityInformation palIdentityInformation,
		@NotNull PalMeasurement palMeasurement
){
}