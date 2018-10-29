package com.example.demo.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 统计目录下所有文件中的数字、字母、空格和行数
 * @author zh
 *
 */
public class SearchFile {

	private static int inte = 0;
	private static int cha = 0;
	private static int kg = 0;
	private static int hang = 0;
	
	
	public static void main(String[] args) {
		search("E:mytest");
		System.out.println();
		System.out.println("inte="+inte+"  cha="+cha+"   kg="+kg+"   hang="+hang);
		
	}

	
	private static void search(String directory) {
		File file = new File(directory);
		if(!file.isDirectory()) {
			try {
				InputStream in = new FileInputStream(file);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				int by = 0;
				while((by = in.read())>0) {
					if(('a'<=by && by<='z') || ('A'<=by && by<='Z')) {
						cha++;
					}
					if('0' <= by && by <= '9') {
						inte++;
					}
					if(by == ' ') {
						kg++;
					}
					System.out.print((char)by);
				}
				
				while(reader.readLine() != null) {
					hang++;
				}
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			String[] directorys = file.list();
			for(String dire : directorys) {
				search(file.getPath()+"/"+dire);
			}
		}
	}
	
}
