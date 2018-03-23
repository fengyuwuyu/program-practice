package com.ll.program.practice.optional;

import java.util.Optional;

import com.ll.program.practice.optional.entity.Car;
import com.ll.program.practice.optional.entity.Insurance;
import com.ll.program.practice.optional.entity.People;

public class OptionalDemo {

	public static void main(String[] args) {
		OptionalDemo demo = new OptionalDemo();
		People people = new People();
		demo.testOptinalMap(Optional.of(people));
	}
	
	private void testOptinalMap(Optional<People> people) {
		System.out.println(people.get());
		String name = people.flatMap(People::getCar)//
				.flatMap(Car::getInsurance)//
				.map(Insurance::getName)//
				.orElse("null");
		System.out.println(name);
	}
}
