package com.example.demo.instrumentation.demo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer{

	public static final String classNumberReturns2 = "TransClass.class.2";
	
	public static byte[] getBytesFromFile(String fileName) {
		try {
			File file = new File(fileName);
			InputStream is = new FileInputStream(file);
			long length = file.length();
			byte[] bytes = new byte[(int) length];
			
			int offset = 0;
			int numRead = 0;
			while(offset < bytes.length && 
					(numRead = is.read(bytes,offset,bytes.length-offset)) >= 0) {
				offset += numRead;
			}
			
			if(offset < bytes.length) {
				throw new IOException("Could not completely read file" + file.getName());
			}
			is.close();
			return bytes;
		}catch(Exception e ) {
			System.out.println("error occurs in _ClassTransformer!" + 
					e.getClass().getName());
			return null;
		}
	}
	
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		if(!className.equals("TransClass")) {
			return null;
		}
		return getBytesFromFile(classNumberReturns2);
	}

}
