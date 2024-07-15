package com.petpals.clients.dto.pals;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record AuthOwner(@Email String email, @NotBlank String password) {

}

