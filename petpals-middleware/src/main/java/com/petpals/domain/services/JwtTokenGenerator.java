package com.petpals.domain.services;


import com.petpals.domain.ports.in.JwtTokenGeneratorPort;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class JwtTokenGenerator implements JwtTokenGeneratorPort {
	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;
	@ConfigProperty(name = "claims.origin")
	public String origin;
	
	@Override
	public String getToken(String email, String caregiverType) {
		return
				Jwt.issuer(issuer)
						.upn(email)
						.groups(new HashSet<>(Arrays.asList(caregiverType)))
						.expiresAt(Instant.now().plus(14, ChronoUnit.DAYS))
						.claim(Claims.address.name(), origin)
						.sign();
	}
}
