package com.petpals.domain.ports.out;


import com.petpals.shared.model.Country;

import java.util.List;

public interface MenuOptionsOut {
	List<Country> getCountries();
}
