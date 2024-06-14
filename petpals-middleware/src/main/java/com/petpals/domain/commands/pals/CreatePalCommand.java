package com.petpals.domain.commands.pals;

import com.petpals.shared.model.dto.PalIdentityInformation;
import com.petpals.shared.model.dto.PalMeasurement;
import com.petpals.shared.model.dto.PalMedicalInformation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public final class CreatePalCommand {
	private @NotBlank
	@Size(min = 32, max = 36) String reference;
	private PalMedicalInformation palMedicalInformation;
	private PalMeasurement palMeasurement;
	private PalIdentityInformation palIdentityInformation;
	
	public CreatePalCommand() {
	}
	
	public CreatePalCommand(PalMedicalInformation palMedicalInformation, PalMeasurement palMeasurement, PalIdentityInformation palIdentityInformation) {
		this.palMedicalInformation = palMedicalInformation;
		this.palMeasurement = palMeasurement;
		this.palIdentityInformation = palIdentityInformation;
	}
	
	public CreatePalCommand(String reference, PalMedicalInformation palMedicalInformation,
							PalMeasurement palMeasurement,
							PalIdentityInformation palIdentityInformation) {
		this.reference = reference;
		this.palMedicalInformation = palMedicalInformation;
		this.palMeasurement = palMeasurement;
		this.palIdentityInformation = palIdentityInformation;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreatePalCommand that = (CreatePalCommand) o;
		return Objects.equals(reference, that.reference) && Objects.equals(palMedicalInformation, that.palMedicalInformation) && Objects.equals(palMeasurement, that.palMeasurement) && Objects.equals(palIdentityInformation, that.palIdentityInformation);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(reference, palMedicalInformation, palMeasurement, palIdentityInformation);
	}
	
	@Override
	public String toString() {
		return "CreatePalCommand{" +
					   "reference='" + reference + '\'' +
					   ", palMedicalInformation=" + palMedicalInformation +
					   ", palMeasurement=" + palMeasurement +
					   ", palIdentityInformation=" + palIdentityInformation +
					   '}';
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public PalMedicalInformation getPalMedicalInformation() {
		return palMedicalInformation;
	}
	
	public void setPalMedicalInformation(PalMedicalInformation palMedicalInformation) {
		this.palMedicalInformation = palMedicalInformation;
	}
	
	public PalMeasurement getPalMeasurement() {
		return palMeasurement;
	}
	
	public void setPalMeasurement(PalMeasurement palMeasurement) {
		this.palMeasurement = palMeasurement;
	}
	
	public PalIdentityInformation getPalIdentityInformation() {
		return palIdentityInformation;
	}
	
	public void setPalIdentityInformation(PalIdentityInformation palIdentityInformation) {
		this.palIdentityInformation = palIdentityInformation;
	}
}
	

