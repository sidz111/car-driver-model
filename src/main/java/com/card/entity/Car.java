package com.card.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "car", uniqueConstraints = { @UniqueConstraint(columnNames = { "car_document", "no_plate" }) })
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String model;
	private Integer noPlate;
	private String carDocument;

	@OneToOne
	@JoinColumn(name = "driver_id") // Corrected reference to driver_id
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
