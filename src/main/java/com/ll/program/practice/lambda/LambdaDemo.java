package com.ll.program.practice.lambda;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.List;

import com.ll.program.practice.entity.Person;

public class LambdaDemo {
	
	final int n = 10;

	public static void main(String[] args) {
		LambdaDemo demo = new LambdaDemo();
		demo.test1();
		
	}
	
	public void test() {
		Runnable task = () -> System.out.println(LambdaDemo.this.n);
	}
	
	public void test(List<Person> persons) {
		persons.sort((person1, person2) -> person1.getAge().compareTo(person2.getAge()));
		persons.sort(comparing(Person::getAge));
	}

	
	public void test1() {
		List<String> str = Arrays.asList("sdg", "asd");
		str.sort(String::compareToIgnoreCase);
		System.out.println(str);
	}
	
}
