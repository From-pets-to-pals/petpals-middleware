package com.petpals.clients.services;

import com.petpals.clients.endpoints.CaregiversHealthCheckClient;
import com.petpals.domain.ports.out.CaregiversHealthCheckOut;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class CaregiversHealthCheck implements CaregiversHealthCheckOut {
	
	@Inject
	@RestClient
	CaregiversHealthCheckClient caregiversHealthCheckClient;
	
	
	@Override
	@CacheResult(cacheName = "hello-cache")
	public String hello() {
		return caregiversHealthCheckClient.hello();
	}
	
	@Override
	@CacheResult(cacheName = "hello-name-cache")
	public String helloYou(String name) {
		return caregiversHealthCheckClient.helloYou(name);
	}
}
