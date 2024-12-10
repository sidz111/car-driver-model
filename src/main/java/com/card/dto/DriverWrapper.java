package com.card.dto;

import java.util.ArrayList;
import java.util.List;

import com.card.entity.Driver;

public class DriverWrapper {
	private List<Driver> drivers = new ArrayList<>();

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
}
