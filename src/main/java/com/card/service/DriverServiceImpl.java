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
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public void setDrivers(List<Driver> drivers) {
        driverRepository.saveAll(drivers);
    }

    @Override
    public List<Driver> addDriversWithCar(List<Driver> drivers) {
        for (Driver d : drivers) {
            Car car = d.getCar();
            if (car != null) {
                carRepository.save(car); // Save car first
            }
        }
        return driverRepository.saveAll(drivers);
    }

    @Override
    public Driver updateDriverWithCar(Integer driverId, Driver updatedDriver) {
        Optional<Driver> existingDriverOpt = driverRepository.findById(driverId);
        if (existingDriverOpt.isPresent()) {
            Driver existingDriver = existingDriverOpt.get();

                          // Update driver's details
            existingDriver.setName(updatedDriver.getName());
            existingDriver.setContact(updatedDriver.getContact());
            existingDriver.setAddress(updatedDriver.getAddress());
            existingDriver.setAdharCard(updatedDriver.getAdharCard());
            existingDriver.setLicense(updatedDriver.getLicense());
            existingDriver.setPhoto(updatedDriver.getPhoto());
            
                                    // Update car if available
            if (updatedDriver.getCar() != null) {
                Car updatedCar = updatedDriver.getCar();
                existingDriver.setCar(updatedCar);
                carRepository.save(updatedCar);
            }

            return driverRepository.save(existingDriver);
        }
        return null;
    }

    @Override
    public Driver getDriverById(Integer driverId) {
        return driverRepository.findById(driverId).orElse(null);
    }

    @Override
    public Driver getDriverByCarId(Integer carId) {
        return driverRepository.findDriverByCarId(carId);
    }

    @Override
    public void deleteDriverById(Integer driverId) {
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        if (driverOpt.isPresent()) {
            Driver driver = driverOpt.get();
            Car car = driver.getCar();

            if (car != null) {
                carRepository.delete(car); // Delete the car first
            }

            driverRepository.delete(driver); // Then delete the driver
        }
    }

	@Override
	public void addDriver(Driver driver) {
		driverRepository.save(driver);
	}
    
}
