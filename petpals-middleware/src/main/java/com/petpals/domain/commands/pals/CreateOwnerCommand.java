package com.petpals.domain.commands.pals;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

public final class CreateOwnerCommand {
	private String email;
	private String username;
	private  String deviceId;
	private String reference;
	private  String location;
	private  List<CreatePalCommand> pals;
	
	public CreateOwnerCommand() {
	}
	
	public CreateOwnerCommand(String email, String username, String deviceId, String reference, String location, List<CreatePalCommand> pals) {
		this.email = email;
		this.username = username;
		this.deviceId = deviceId;
		this.reference = reference;
		this.location = location;
		this.pals = pals;
	}
	
	public CreateOwnerCommand(@Email String email, @NotBlank String deviceId, @NotBlank String reference,
							  @NotBlank String location, @NotNull List<CreatePalCommand> pals) {
		this.email = email;
		this.deviceId = deviceId;
		this.reference = reference;
		this.location = location;
		this.pals = pals;
	}
	
	public CreateOwnerCommand(String email, String deviceId, String location, List<CreatePalCommand> pals) {
		this.email = email;
		this.deviceId = deviceId;
		this.location = location;
		this.pals = pals;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<CreatePalCommand> getPals() {
		return pals;
	}
	
	public void setPals(List<CreatePalCommand> pals) {
		this.pals = pals;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (CreateOwnerCommand) obj;
		return Objects.equals(this.email, that.email) &&
					   Objects.equals(this.deviceId, that.deviceId) &&
					   Objects.equals(this.reference, that.reference) &&
					   Objects.equals(this.location, that.location) &&
					   Objects.equals(this.pals, that.pals);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, deviceId, reference, location, pals);
	}
	
	@Override
	public String toString() {
		return "NewOwner[" +
					   "email=" + email + ", " +
					   "deviceId=" + deviceId + ", " +
					   "reference=" + reference + ", " +
					   "location=" + location + ", " +
					   "pals=[" + pals + ']';
	}
	
	
}

