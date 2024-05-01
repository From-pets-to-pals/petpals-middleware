package com.petpals.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOwnerRequest(@Email String email, @NotBlank String deviceId, @NotBlank String reference,
								 @NotBlank String location, @NotNull List<CreatePalRequest> pals) {
}
