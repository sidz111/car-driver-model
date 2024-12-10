package com.card.dto;

import java.util.List;

import com.card.entity.Car;

public class CarWrapper {

	private List<Car> cars;

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
