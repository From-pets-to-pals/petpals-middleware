package com.petpals.domain.services;

import com.petpals.domain.ports.in.MenuOptionsIn;
import com.petpals.domain.ports.out.MenuOptionsOut;
import com.petpals.shared.model.dto.Breed;
import com.petpals.shared.model.dto.BreedWithoutSpecie;
import com.petpals.shared.model.dto.Country;
import com.petpals.shared.model.dto.Specie;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MenuOptionsService implements MenuOptionsIn {
	
	private final MenuOptionsOut menuOptionsOut;
	
	public MenuOptionsService(MenuOptionsOut menuOptionsOut) {
		this.menuOptionsOut = menuOptionsOut;
	}
	
	@Override
	public List<Country> getCountries() {
		return menuOptionsOut.getCountries();
	}
	
	@Override
	public List<Breed> getBreeds() {
		return menuOptionsOut.getBreeds();
	}
	
	@Override
	public List<BreedWithoutSpecie> getDogBreeds() {
		return menuOptionsOut.getDogBreeds();
	}
	
	@Override
	public List<BreedWithoutSpecie> getCatBreeds() {
		return menuOptionsOut.getCatBreeds();
		
	}
	
	@Override
	public List<BreedWithoutSpecie> getNacBreeds() {
		return menuOptionsOut.getNacBreeds();
		
	}
	
	@Override
	public List<Specie> getSpecies() {
		return menuOptionsOut.getSpecies();
	}
}
