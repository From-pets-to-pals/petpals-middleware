package com.petpals.application.dto;


import com.petpals.shared.model.PalIdentityInformation;
import com.petpals.shared.model.PalMeasurement;
import com.petpals.shared.model.PalMedicalInformation;
import jakarta.validation.constraints.NotNull;

public record CreatePalRequest(
		@NotNull PalMedicalInformation palMedicalInformation,
		@NotNull PalIdentityInformation palIdentityInformation,
		@NotNull PalMeasurement palMeasurement
){
}