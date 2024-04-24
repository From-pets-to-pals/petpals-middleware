package com.petpals.domain.ports.in;

public interface JwtTokenGeneratorPort {
	String getToken(String email, String caregiverType);
}
