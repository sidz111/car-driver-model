package com.card.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.entity.Car;
import com.card.entity.Driver;
import com.card.repository.CarRepository;
import com.card.repository.DriverRepository;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepository carRepository;

	@Autowired
	DriverRepository driverRepository;

	@Override
	public Car addCarWithDriver(Car car) {
		Driver driver = car.getDriver();
		if (driver != null) {
			driverRepository.save(driver);
		}
		return carRepository.save(car);
	}

	@Override
	public Car updateCarWithDriver(Integer carId, Car updatedCar) {
		Optional<Car> existingCarOpt = carRepository.findById(carId);
		if (existingCarOpt.isPresent()) {
			Car existingCar = existingCarOpt.get();

			existingCar.setName(updatedCar.getName());
			existingCar.setModel(updatedCar.getModel());
			existingCar.setNoPlate(updatedCar.getNoPlate());
			existingCar.setCarDocument(updatedCar.getCarDocument());

			Driver updatedDriver = updatedCar.getDriver();
			if (updatedDriver != null) {
				existingCar.setDriver(updatedDriver);
				driverRepository.save(updatedDriver);
			}

			return carRepository.save(existingCar);
		}
		return null;
	}

	@Override
	public Car getCarById(Integer carId) {
		Optional<Car> car = carRepository.findById(carId);
		return car.orElse(null);
	}

	@Override
	public Car getCarByDriverName(String driverName) {
		return carRepository.findCarByDriverName(driverName);
	}

	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public void deleteCarById(Integer carId) {
		Optional<Car> carOpt = carRepository.findById(carId);
		if (carOpt.isPresent()) {
			Car car = carOpt.get();
			Driver driver = car.getDriver();

			if (driver != null) {
				driverRepository.delete(driver);
			}
			carRepository.delete(car);
		}
	}

	@Override
	public void addCar(Car car) {
		carRepository.save(car);
	}
}
