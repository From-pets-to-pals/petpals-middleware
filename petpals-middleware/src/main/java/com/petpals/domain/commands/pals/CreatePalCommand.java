package com.petpals.domain.commands.pals;

import com.petpals.shared.enums.Species;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.Objects;

public final class CreatePalCommand {
	private final boolean isVaccinated;
	private final @NotBlank
	@Size(min = 32, max = 36) String reference;
	private final @NotBlank
	@Size(min = 2) String name;
	private final @NotBlank
	@Size(min = 2) String shortName;
	private final @Past Date birthDate;
	private final @NotNull boolean isMale;
	private final @NotNull Species specie;
	private final @Size(min = 5, max = 25) String breed;
	private final @Pattern(regexp = "^[250(26|22)\\d{10}]{15}$", message = "Identifier format invalid") String icadIdentifier;
	private final @NotNull Boolean hasPassport;
	private final @NotNull Boolean isSterilized;
	private final @DecimalMin(value = "0.1")
	@DecimalMax(value = "50.0") double weight;
	private final @DecimalMin(value = "0.1")
	@DecimalMax(value = "150.0") double height;
	
	public CreatePalCommand(
			boolean isVaccinated,
			@NotBlank @Size(min = 32, max = 36) String reference,
			@NotBlank @Size(min = 2) String name,
			@NotBlank @Size(min = 2) String shortName,
			@Past Date birthDate,
			@NotNull boolean isMale,
			@NotNull Species specie,
			@Size(min = 5, max = 25) String breed,
			@Pattern(regexp = "^[250(26|22)\\d{10}]{15}$", message = "Identifier format invalid") String icadIdentifier,
			@NotNull Boolean hasPassport,
			@NotNull Boolean isSterilized,
			@DecimalMin(value = "0.1") @DecimalMax(value = "50.0") double weight,
			@DecimalMin(value = "0.1") @DecimalMax(value = "150.0") double height) {
		this.isVaccinated = isVaccinated;
		this.reference = reference;
		this.name = name;
		this.shortName = shortName;
		this.birthDate = birthDate;
		this.isMale = isMale;
		this.specie = specie;
		this.breed = breed;
		this.icadIdentifier = icadIdentifier;
		this.hasPassport = hasPassport;
		this.isSterilized = isSterilized;
		this.weight = weight;
		this.height = height;
	}
	
	public boolean isVaccinated() {
		return isVaccinated;
	}
	
	public @NotBlank @Size(min = 32, max = 36) String reference() {
		return reference;
	}
	
	public @NotBlank @Size(min = 2) String name() {
		return name;
	}
	
	public @NotBlank @Size(min = 2) String shortName() {
		return shortName;
	}
	
	public @Past Date birthDate() {
		return birthDate;
	}
	
	public @NotNull boolean isMale() {
		return isMale;
	}
	
	public @NotNull Species specie() {
		return specie;
	}
	
	public @Size(min = 5, max = 25) String breed() {
		return breed;
	}
	
	public @Pattern(regexp = "^[250(26|22)\\d{10}]{15}$", message = "Identifier format invalid") String icadIdentifier() {
		return icadIdentifier;
	}
	
	public @NotNull Boolean hasPassport() {
		return hasPassport;
	}
	
	public @NotNull Boolean isSterilized() {
		return isSterilized;
	}
	
	public @DecimalMin(value = "0.1") @DecimalMax(value = "50.0") double weight() {
		return weight;
	}
	
	public @DecimalMin(value = "0.1") @DecimalMax(value = "150.0") double height() {
		return height;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (CreatePalCommand) obj;
		return this.isVaccinated == that.isVaccinated &&
					   Objects.equals(this.reference, that.reference) &&
					   Objects.equals(this.name, that.name) &&
					   Objects.equals(this.shortName, that.shortName) &&
					   Objects.equals(this.birthDate, that.birthDate) &&
					   this.isMale == that.isMale &&
					   Objects.equals(this.specie, that.specie) &&
					   Objects.equals(this.breed, that.breed) &&
					   Objects.equals(this.icadIdentifier, that.icadIdentifier) &&
					   Objects.equals(this.hasPassport, that.hasPassport) &&
					   Objects.equals(this.isSterilized, that.isSterilized) &&
					   Double.doubleToLongBits(this.weight) == Double.doubleToLongBits(that.weight) &&
					   Double.doubleToLongBits(this.height) == Double.doubleToLongBits(that.height);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(isVaccinated, reference, name, shortName, birthDate, isMale, specie, breed, icadIdentifier, hasPassport, isSterilized, weight, height);
	}
	
	@Override
	public String toString() {
		return "AddFirstPal[" +
					   "isVaccinated=" + isVaccinated + ", " +
					   "reference=" + reference + ", " +
					   "name=" + name + ", " +
					   "shortName=" + shortName + ", " +
					   "birthDate=" + birthDate + ", " +
					   "isMale=" + isMale + ", " +
					   "specie=" + specie + ", " +
					   "breed=" + breed + ", " +
					   "icadIdentifier=" + icadIdentifier + ", " +
					   "hasPassport=" + hasPassport + ", " +
					   "isSterilized=" + isSterilized + ", " +
					   "weight=" + weight + ", " +
					   "height=" + height + ']';
	}
	
}
	

