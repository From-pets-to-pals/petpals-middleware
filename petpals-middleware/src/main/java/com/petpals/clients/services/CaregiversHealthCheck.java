package com.petpals.clients.services;

import com.petpals.clients.endpoints.CaregiversHealthCheckClient;
import com.petpals.domain.ports.out.CaregiversHealthCheckOut;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

@ApplicationScoped
public class CaregiversHealthCheck implements CaregiversHealthCheckOut {
	private final Logger logger = Logger.getLogger(CaregiversHealthCheck.class);
	
	@Inject
	@RestClient
	CaregiversHealthCheckClient caregiversHealthCheckClient;
	
	
	@Override
	@CacheResult(cacheName = "hello-cache")
	public String hello() {
		try {
			return caregiversHealthCheckClient.hello();
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e){
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
	
	@Override
	@CacheResult(cacheName = "hello-name-cache")
	public String helloYou(String name) {
		try {
			return caregiversHealthCheckClient.helloYou(name);
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e){
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
}
