package com.ll.program.practice.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.ll.program.practice.entity.Person;
import com.ll.program.practice.utils.CommonUtil;

public class StreamDemo {
	
	List<Person> persons = null;
	
	@Test
	public void list(List<String> list) {
		list.sort((String str1, String str2) -> str1.compareTo(str2));
	}
	
	@Before
	public void init() {
		try {
			persons = CommonUtil.createPersons(10, Person.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test01() throws Exception {
		System.out.println(persons);
		List<String> ages = persons.parallelStream()//
									.filter((p) -> p.getAge() > 0)//
//									.sorted((p1, p2) -> p1.getAge().compareTo(p2.getAge()))
									.sorted(Comparator.comparing(Person::getName))
									.map(Person::getName)//
									.collect(Collectors.toList());
		System.out.println(ages);
	}

	@Test
	public void sort() throws Exception {
		persons = persons.parallelStream()//
		.sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
		System.out.println(persons);
		
	}
	
	@Test
	public void groupBy() {
		Map<Integer, List<Person>> map = persons.parallelStream()//
					.collect(Collectors.groupingBy(Person::getAge));
		
		for (Map.Entry<Integer, List<Person>> entry : map.entrySet()) {
			System.err.println(String.format("key = %d, value = %s", entry.getKey(), entry.getValue()));
		}
	}
}
