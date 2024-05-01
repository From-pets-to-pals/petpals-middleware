package com.petpals.domain.model;

import com.petpals.shared.enums.Species;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record PalIdentityInformation (
		@NotNull
		@Size(min = 2, max=25, message = "Taille du nom de l'animal incorrecte")
		String name,
		@Past(message = "La date doit être passée")
		@NotNull
		Date birthDate,
		@NotNull
		boolean isMale,
		@SpeciesConstraint(enumClass = Species.class, message = "Invalid specie selected")
		Species specie,
		@Size(min=3, max=25, message="")
		String race,
		@Pattern(regexp = "^[250(26|22)\\d{10}]{15}$")
		String icadIdentifier,
		@NotNull
		boolean hasPassport
){}