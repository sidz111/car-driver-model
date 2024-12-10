package com.card.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.card.dto.CarWrapper;
import com.card.dto.DriverWrapper;
import com.card.entity.Car;
import com.card.entity.Driver;
import com.card.service.CarService;
import com.card.service.DriverService;

@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;
    
    @Autowired
    private CarService carService;
    
    @GetMapping("/")
    public String getAllDrivers(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "index";
    }

    @GetMapping("/create")
    public String showDriverCountForm(Model model) {
    	DriverWrapper driverWrapper = new DriverWrapper();
    	CarWrapper carWrapper = new CarWrapper();
        driverWrapper.setDrivers(new ArrayList<>());
        carWrapper.setCars(new ArrayList<>());
        model.addAttribute("drivers", driverWrapper);
        model.addAttribute("cars", carWrapper);
        return "drivers";
    }

    @PostMapping("/create")
    public String createDrivers(@RequestParam("count") int count, Model model) {
        List<Driver> drivers = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            drivers.add(new Driver());
            cars.add(new Car());
        }

        model.addAttribute("drivers", drivers);
        model.addAttribute("cars", cars);
        return "driver-form";
    }

    @PostMapping("/save")
    public String saveDrivers(@ModelAttribute("drivers") DriverWrapper driverListWrapper,
                              @ModelAttribute("cars") CarWrapper carListWrapper) {
        if (driverListWrapper.getDrivers() == null) {
            driverListWrapper.setDrivers(new ArrayList<>());
        }
        if (carListWrapper.getCars() == null) {
            carListWrapper.setCars(new ArrayList<>());
        }

        List<Driver> drivers = driverListWrapper.getDrivers();
        List<Car> cars = carListWrapper.getCars();
        
        for (Driver driver : drivers) {
            driverService.addDriver(driver);
        }
        
        for (Car car: cars) {
        	carService.addCar(car);
        }

        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            Car car = cars.get(i);

            driver.setCar(car);
            car.setDriver(driver);
        }

        for (Car car : cars) {
            carService.addCarWithDriver(car);
        }

        return "redirect:/";
    }

    
    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") Integer id) {
        Driver driver = driverService.getDriverById(id);
        if (driver != null && driver.getCar() != null) {
            Car car = driver.getCar();
            carService.deleteCarById(car.getId());
        }
        driverService.deleteDriverById(id);
        return "redirect:/";
    }

}
