package com.petpals.application.dto.pals;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AuthOwnerRequest(@Email String email, String password) {
}
