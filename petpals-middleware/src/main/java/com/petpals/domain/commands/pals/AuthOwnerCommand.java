package com.petpals.domain.commands.pals;

import java.util.List;
import java.util.Objects;

public final class AuthOwnerCommand {
	private String email;
	private String password;

	public AuthOwnerCommand() {
	}

	public AuthOwnerCommand(String email, String password) {
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
		AuthOwnerCommand that = (AuthOwnerCommand) o;
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

