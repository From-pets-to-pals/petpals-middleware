package com.petpals.application.dto.caregivers;

import com.petpals.application.dto.pals.CreatePalRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateCaregiverRequest(    @NotBlank String address,
                                         @NotNull Double appointmentDuration,
                                         @NotBlank String caregiverType,
                                         @NotBlank String city,
                                         @NotBlank String country,
                                         @Email String email,
                                         @NotBlank String password,
                                         @NotBlank String firstName,
                                         @NotNull Boolean homeService,
                                         @NotNull Boolean subscribed,
                                         @NotBlank String lastName,
                                         @NotNull List<String> palsHandled,
                                         @NotBlank String phoneNumber,
                                         @NotNull Double priceRating,
                                         @NotNull Double serviceRating,
                                         @NotNull List<String> workingDays,
                                         @NotBlank String zipCode) {
}
