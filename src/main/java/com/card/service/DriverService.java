package com.card.service;

import java.util.List;

import com.card.entity.Driver;

public interface DriverService {
	List<Driver> getAllDrivers();
	void setDrivers(List<Driver> drivers);
	
	
    List<Driver> addDriversWithCar(List<Driver> drivers);

    Driver updateDriverWithCar(Integer driverId, Driver updatedDriver);

    Driver getDriverById(Integer driverId);

    Driver getDriverByCarId(Integer carId);

    void deleteDriverById(Integer driverId);

	void addDriver(Driver driver);

}
