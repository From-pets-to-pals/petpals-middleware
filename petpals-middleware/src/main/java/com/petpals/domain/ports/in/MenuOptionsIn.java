package com.petpals.domain.ports.in;

import com.petpals.application.dto.menus.Country;

import java.util.List;

public interface MenuOptionsIn {
	List<Country> getCountries();
}
