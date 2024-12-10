package com.card.service;

import java.util.List;

import com.card.entity.Car;


public interface CarService {

	Car addCarWithDriver(Car car);

	Car updateCarWithDriver(Integer carId, Car updatedCar);

	Car getCarById(Integer carId);

	Car getCarByDriverName(String driverName);

	List<Car> getAllCars();

	void deleteCarById(Integer carId);
	
	void addCar(Car car);
}
