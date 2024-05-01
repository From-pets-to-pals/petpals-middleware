package com.petpals.domain.model;

import jakarta.validation.constraints.Future;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class PalMedicalInformation {
	private boolean isVaccinated;
	private List<String> medicalHistory;
	private @Future Date nextVaccine;
	private @Future Date nextPlannedVetApp;
	private boolean isSterilized;
	
	public PalMedicalInformation() {
	}
	
	public PalMedicalInformation(
			
			boolean isVaccinated,
			List<String> medicalHistory,
			@Future
			Date nextVaccine,
			@Future
			Date nextPlannedVetApp,
			boolean isSterilized
	) {
		this.isVaccinated = isVaccinated;
		this.medicalHistory = medicalHistory;
		this.nextVaccine = nextVaccine;
		this.nextPlannedVetApp = nextPlannedVetApp;
		this.isSterilized = isSterilized;
	}
	
	public boolean isVaccinated() {
		return isVaccinated;
	}
	
	public List<String> medicalHistory() {
		return medicalHistory;
	}
	
	public @Future Date nextVaccine() {
		return nextVaccine;
	}
	
	public @Future Date nextPlannedVetApp() {
		return nextPlannedVetApp;
	}
	
	public boolean isSterilized() {
		return isSterilized;
	}
	
	public void setVaccinated(boolean vaccinated) {
		isVaccinated = vaccinated;
	}
	
	public void setNextVaccine(Date nextVaccine) {
		this.nextVaccine = nextVaccine;
	}
	
	public void setNextPlannedVetApp(Date nextPlannedVetApp) {
		this.nextPlannedVetApp = nextPlannedVetApp;
	}
	
	public void setSterilized(boolean sterilized) {
		isSterilized = sterilized;
	}
	
	public List<String> getMedicalHistory() {
		return medicalHistory;
	}
	
	public void setMedicalHistory(List<String> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	
	public Date getNextVaccine() {
		return nextVaccine;
	}
	
	public Date getNextPlannedVetApp() {
		return nextPlannedVetApp;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (PalMedicalInformation) obj;
		return this.isVaccinated == that.isVaccinated &&
					   Objects.equals(this.medicalHistory, that.medicalHistory) &&
					   Objects.equals(this.nextVaccine, that.nextVaccine) &&
					   Objects.equals(this.nextPlannedVetApp, that.nextPlannedVetApp) &&
					   this.isSterilized == that.isSterilized;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(isVaccinated, medicalHistory, nextVaccine, nextPlannedVetApp, isSterilized);
	}
	
	@Override
	public String toString() {
		return "PalMedicalInformation[" +
					   "isVaccinated=" + isVaccinated + ", " +
					   "medicalHistory=" + medicalHistory + ", " +
					   "nextVaccine=" + nextVaccine + ", " +
					   "nextPlannedVetApp=" + nextPlannedVetApp + ", " +
					   "isSterilized=" + isSterilized + ']';
	}
	
	
}