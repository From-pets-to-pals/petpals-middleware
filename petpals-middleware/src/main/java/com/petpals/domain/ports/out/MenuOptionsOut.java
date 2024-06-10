package com.petpals.domain.ports.out;

import com.petpals.application.dto.menus.Country;

import java.util.List;

public interface MenuOptionsOut {
	List<Country> getCountries();
}
