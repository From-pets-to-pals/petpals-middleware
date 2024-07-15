package com.petpals.application.dto.pals;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CreateOwnerRequest(@Email String email, @NotBlank String password, @NotBlank String deviceId, @NotBlank String username,
								 @NotBlank String location, @NotNull List<CreatePalRequest> pals) {
}
