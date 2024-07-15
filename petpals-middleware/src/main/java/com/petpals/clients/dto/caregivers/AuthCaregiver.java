package com.petpals.clients.dto.caregivers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record AuthCaregiver(@Email String email, @NotBlank String password) {

}

