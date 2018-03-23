package com.ll.program.practice.jvm;

import java.util.ArrayList;
import java.util.List;
/**
 * ģ��OutOfMemoryError�쳣��
 * VM args��-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author ll
 *
 */
public class TestOutOfMemory {
	
	static class A {

	}

	public static void main(String[] args) {
		List<A> list = new ArrayList<>();
		while(true){
			list.add(new A());
		}
	}

}
