package com.petpals.domain.services;

import com.petpals.domain.ports.in.MenuOptionsIn;
import com.petpals.domain.ports.out.MenuOptionsOut;
import com.petpals.shared.model.Breed;
import com.petpals.shared.model.Country;
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
		return new ArrayList<>();
	}
}
