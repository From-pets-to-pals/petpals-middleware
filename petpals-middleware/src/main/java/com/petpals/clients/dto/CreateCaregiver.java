package com.petpals.clients.dto;


import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;

import java.util.Arrays;
import java.util.Objects;

public record CreateCaregiver(String firstName, String lastName, String email, String phoneNumber, String address,
                              String city, String zipCode, String country, Days[] workingDays, Species[] palsHandled,
                              boolean homeService, double appointmentDuration,
                              CaregiverTypes caregiverType, boolean isSubscribed, double serviceRating,
                              double priceRating) {
}
