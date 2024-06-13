package com.petpals.clients.services.menus;

import com.petpals.clients.endpoints.MenuOptionsClient;
import com.petpals.domain.ports.out.MenuOptionsOut;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import com.petpals.shared.model.Breed;
import com.petpals.shared.model.BreedWithoutSpecie;
import com.petpals.shared.model.Country;
import com.petpals.shared.model.Specie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyClientErrorException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import java.util.List;

@ApplicationScoped
public class MenuOptionsClientService implements MenuOptionsOut{
	
	private final Logger logger = Logger.getLogger(MenuOptionsClientService.class);
	
	MenuOptionsClient menuOptionsClient;
	@Inject
	public MenuOptionsClientService(@RestClient MenuOptionsClient menuOptionsClient) {
		this.menuOptionsClient = menuOptionsClient;
	}
	@Override
	public List<Country> getCountries() {
		try {
			return menuOptionsClient.getCountries();
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
	
	@Override
	public List<Breed> getBreeds() {
		try {
			return menuOptionsClient.getBreeds();
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
	
	@Override
	public List<BreedWithoutSpecie> getDogBreeds() {
		try {
			return menuOptionsClient.getDogBreeds();
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
	
	@Override
	public List<BreedWithoutSpecie> getCatBreeds() {
		try {
			return menuOptionsClient.getCatBreeds();
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
	
	@Override
	public List<BreedWithoutSpecie> getNacBreeds() {
		try {
			return menuOptionsClient.getNacBreeds();
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
	
	@Override
	public List<Specie> getSpecies() {
		try {
			return menuOptionsClient.getSpecies();
		} catch (ResteasyWebApplicationException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_OFFLINE_REST_CLIENT_EXCEPTION);
		} catch (ResteasyClientErrorException e) {
			logger.info(e.toString());
			throw new PetPalsExceptions(
					ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
		}
	}
}
