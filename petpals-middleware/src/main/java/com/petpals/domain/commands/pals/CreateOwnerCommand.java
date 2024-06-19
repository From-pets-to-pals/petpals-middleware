package com.petpals.domain.commands.pals;

import java.util.List;
import java.util.Objects;

public final class CreateOwnerCommand {
	private String email;
	private String password;
	private String username;
	private  String deviceId;
	private String reference;
	private  String location;
	private  List<CreatePalCommand> pals;
	
	public CreateOwnerCommand() {
	}
	
	public CreateOwnerCommand(String email, String password, String username, String deviceId, String reference, String location, List<CreatePalCommand> pals) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.deviceId = deviceId;
		this.reference = reference;
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateOwnerCommand that = (CreateOwnerCommand) o;
		return Objects.equals(email, that.email) && Objects.equals(username, that.username) && Objects.equals(deviceId, that.deviceId) && Objects.equals(reference, that.reference) && Objects.equals(location, that.location) && Objects.equals(pals, that.pals);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, username, deviceId, reference, location, pals);
	}
	
	@Override
	public String toString() {
		return "CreateOwnerCommand{" +
					   "email='" + email + '\'' +
					   ", username='" + username + '\'' +
					   ", deviceId='" + deviceId + '\'' +
					   ", reference='" + reference + '\'' +
					   ", location='" + location + '\'' +
					   ", pals=" + pals +
					   '}';
	}
}

