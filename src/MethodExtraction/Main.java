package MethodExtraction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String args[]) throws IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader("tests/file_list.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	//String infile = line;
		    	String infile = "tests/MainActivity.java";
				if(args.length > 0) 
		           infile = args[0];
				JavaMethodExtractor methodExtractor = new JavaMethodExtractor(infile);
				methodExtractor.extract();
				try {
					HashMap<String, String> methodBodies = methodExtractor.getMethodBodies(); // methodSignature => methodBody
					HashMap<String, ArrayList<String>> apiCalls = methodExtractor.getAPICalls(); //methodSignature => ArrayList of API Calls
					for (String key : methodBodies.keySet()) {
						   System.out.println("------------------------------------------------");
						   System.out.println("Iterating or looping map using java5 foreach loop");
						   System.out.println("key: " + key + " value: " + methodBodies.get(key));
						}
					for (String key : apiCalls.keySet()) {
						   System.out.println("------------------------------------------------");
						   System.out.println("Iterating or looping map using java5 foreach loop");
						   System.out.println("key: " + key + " value: " + apiCalls.get(key));
						}

				} catch (Exception e) {
					e.printStackTrace();
				} 
				
		    }
		} 
	
	 }

}
