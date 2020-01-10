package com.example.demo.face;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {

		int[] arr = new int[] {4,2,5,33,2,77,34,13};
//		maoPaoSort(arr);
		xuanzeSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void maoPaoSort(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length-1;j++) {
				if(arr[j] > arr[j+1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
	}
	
	public static void xuanzeSort(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=i;j<arr.length-1;j++) {
				if(arr[i] > arr[j+1]) {
					int tmp = arr[i];
					arr[i] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
	}
	
	public static void kuaisuSort(int[] arr) {
		
		
		
	}
	
}
