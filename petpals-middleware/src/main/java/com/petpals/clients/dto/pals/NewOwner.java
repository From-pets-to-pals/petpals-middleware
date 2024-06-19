package com.petpals.clients.dto.pals;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewOwner(@Email String email, @NotBlank String password, @NotBlank String username, @NotBlank String deviceId,
					   @NotBlank String reference,
					   @NotBlank String location, @NotNull List<AddFirstPal> pals) {

}

