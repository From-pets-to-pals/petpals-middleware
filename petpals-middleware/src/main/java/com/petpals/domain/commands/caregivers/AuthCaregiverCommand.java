package com.petpals.domain.commands.caregivers;

import java.util.Objects;

public final class AuthCaregiverCommand {
	private String email;
	private String password;

	public AuthCaregiverCommand() {
	}

	public AuthCaregiverCommand(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
		AuthCaregiverCommand that = (AuthCaregiverCommand) o;
		return Objects.equals(email, that.email) && Objects.equals(password, that.password);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}
	
	@Override
	public String toString() {
		return "CreateOwnerCommand{" +
					   "email='" + email + '\'' +
					   ", password='" + password +
					   '}';
	}
}

