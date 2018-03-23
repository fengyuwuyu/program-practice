package com.ll.program.practice.optional.entity;

import java.io.Serializable;
import java.util.Optional;

public class People implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7364303261943932495L;
	private Optional<Car> car;

	public Optional<Car> getCar() {
		return car;
	}

	public void setCar(Optional<Car> car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "People [car=" + car + "]";
	}

}
