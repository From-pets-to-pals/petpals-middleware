package com.petpals.domain.services;

import com.petpals.application.dto.menus.Country;
import com.petpals.domain.ports.in.MenuOptionsIn;
import com.petpals.domain.ports.out.MenuOptionsOut;
import jakarta.enterprise.context.ApplicationScoped;

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
}
