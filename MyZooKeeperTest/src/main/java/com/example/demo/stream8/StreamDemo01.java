package com.example.demo.stream8;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class StreamDemo01 {

	public static void main(String[] args) {
		Stream<String> stream1 = Stream.of("A","B","C");
		System.out.println("stream1:" + stream1.collect(joining()));

		
		String[] values = new String[] {"A","B","C"};
		Stream<String> stream2 = Stream.of(values);
		
		
		Stream<String> stream3 = Arrays.stream(values);	
		
	}

	private static Collector joining() {
		
		return null;
	}

}
