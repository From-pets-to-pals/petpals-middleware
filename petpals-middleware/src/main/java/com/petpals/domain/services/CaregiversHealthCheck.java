package com.petpals.domain.services;

import com.petpals.domain.ports.in.CaregiversHealthCheckIn;
import com.petpals.domain.ports.out.CaregiversHealthCheckOut;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaregiversHealthCheck implements CaregiversHealthCheckIn {
	CaregiversHealthCheckOut caregiversHealthCheckOut;
	
	public CaregiversHealthCheck(CaregiversHealthCheckOut caregiversHealthCheckOut) {
		this.caregiversHealthCheckOut = caregiversHealthCheckOut;
	}
	
	@Override
	public String hello() {
		return caregiversHealthCheckOut.hello();
	}
	
	@Override
	public String helloYou(String name) {
		return caregiversHealthCheckOut.helloYou(name);
	}
}
