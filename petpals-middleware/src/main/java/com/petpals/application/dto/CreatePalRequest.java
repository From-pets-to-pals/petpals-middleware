package com.petpals.application.dto;


import com.petpals.domain.model.PalIdentityInformation;
import com.petpals.domain.model.PalMeasurement;
import com.petpals.domain.model.PalMedicalInformation;
import com.petpals.shared.enums.Species;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.Objects;

public record CreatePalRequest(
		@NotNull PalIdentityInformation palIdentityInformation,
		@NotNull PalMedicalInformation palMedicalInformation,
		@NotNull PalMeasurement palMeasurement
){
}