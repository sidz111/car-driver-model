package com.card.dto;

import com.card.entity.Driver;

public class Car {

	private Integer id;
	private String name;
	private String model;
	private Integer noPlate;
	private String carDocument;

	private Driver driver;

	// Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getNoPlate() {
		return noPlate;
	}

	public void setNoPlate(Integer noPlate) {
		this.noPlate = noPlate;
	}

	public String getCarDocument() {
		return carDocument;
	}

	public void setCarDocument(String carDocument) {
		this.carDocument = carDocument;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}
