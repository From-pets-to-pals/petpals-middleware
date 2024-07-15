package com.petpals.application.dto.caregivers;

import jakarta.validation.constraints.Email;

public record AuthCaregiverRequest(@Email String email, String password) {
}
