package com.example.demo;

@FunctionalInterface
public interface IntTest2 {

	public String t_a(String name);
	
	default String t_b() {
		return "ededadf";
	}
}
