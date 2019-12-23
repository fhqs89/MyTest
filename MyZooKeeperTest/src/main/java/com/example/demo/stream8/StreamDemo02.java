package com.example.demo.stream8;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamDemo02 {

	public static void main(String[] args) {	
		//------------list
		List<String> list = Arrays.asList("A","B","C");
		Stream<String> stream4 = list.stream();
		
//		System.out.println(stream4.collect(collector));
		
		//------------set
		Set<String> set = new HashSet<String>(Arrays.asList("A","B","C"));
		Stream<String> stream5 = set.stream();
		
		System.out.println();
		
		//------------map
		Map<String, String> map = new HashMap<>();
		map.put("1", "A");
		map.put("2", "B");
		map.put("3", "C");
		Stream<String> stream6 = map.values().stream();
		
		System.out.println();
		
		//------------Stream.iterate
		Stream<String> stream7 = 
				Stream.iterate("A", e -> String.valueOf((char)(e.charAt(0)+1))).limit(3);
		
		System.out.println();
		
		//------------Pattern
		String value = "A B C";
		Stream<String> stream8 = Pattern.compile("\\W").splitAsStream(value);
		
		System.out.println();
		
		//------------File.lines
		try {
			Stream<String> stream9 = Files.lines(Paths.get("d:test.txt"));
			System.out.println();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//------------Stream.generate
		Stream<String> stream10 = Stream.generate(()-> "A").limit(3);
		System.out.println();
	}

}
