package com.ll.program.practice.jvm;

public class TestClassLoad {

	public static void main(String[] args) {
//		Student.st();
		System.out.println(Student.a);
	}
}

interface People{
	static int i = 10;
	
}
class Supper implements People{
	protected static int supper = 10;
	static{
		System.out.println("supper---"+supper);
	}
}

class Child extends Supper{
	protected static int a = 10;
	static{
		System.out.println("Child---"+a);
	}
	
	public static int getA(){
		return a;
	}
}

class Student extends Child{
	
	public static void st(){
		System.out.println("asf");
	}
}