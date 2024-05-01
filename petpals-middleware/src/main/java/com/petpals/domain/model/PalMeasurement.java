package com.petpals.domain.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record PalMeasurement(
		@DecimalMin(value = "0.1") @DecimalMax(value = "200.0") Double weight,
		@DecimalMin(value = "0.1") @DecimalMax(value = "200.0") Double height
) {
}
