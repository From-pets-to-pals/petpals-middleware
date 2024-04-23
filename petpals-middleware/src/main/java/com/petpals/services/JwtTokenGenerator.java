package com.petpals.services;


import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class JwtTokenGenerator {
	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;
	@ConfigProperty(name = "claims.origin")
	public String origin;
	
	public String getToken(String email) {
		return
				Jwt.issuer(issuer)
						.upn(email)
						.groups(new HashSet<>(Arrays.asList("Owners", "Caregivers")))
						.expiresAt(Instant.now().plus(14, ChronoUnit.DAYS))
						.claim(Claims.address.name(), origin)
						.sign();
	}
}
