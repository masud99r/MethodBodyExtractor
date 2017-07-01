package MethodExtraction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Main {
	public static void main(String args[]) throws IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader("tests/file_list.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String infile = line;
		    	//String infile = "tests/MainActivity.java";
				if(args.length > 0) 
		           infile = args[0];
				JavaMethodExtractor methodExtractor = new JavaMethodExtractor(infile);
				methodExtractor.extract();
				try {
					HashMap<String, String> methodBodies = methodExtractor.getMethodBodies(); // methodSignature => methodBody
					HashMap<String, ArrayList<String>> apiCalls = methodExtractor.getAPICalls(); //methodSignature => ArrayList of API Calls
<<<<<<< HEAD
					/*
=======

>>>>>>> branch 'master' of https://github.com/masud99r/MethodBodyExtractor.git
					for (String key : methodBodies.keySet()) {
						   System.out.println("------------------------------------------------");
						   System.out.println("Iterating or looping map using java5 foreach loop");
						   System.out.println("key: " + key + " value: " + methodBodies.get(key));
						}
						*/
					for (String key : apiCalls.keySet()) {
						   System.out.println("------------------------------------------------");
						   System.out.println("key: " + key + " value: " + apiCalls.get(key));
						}


					System.out.println(infile);
					Set<String> methodSigs = methodBodies.keySet();
					for (String methodSig: methodSigs){
						System.out.println("\n\n" + methodSig);
						System.out.println(methodBodies.get(methodSig));
						ArrayList<String> apis = apiCalls.get(methodSig);
						for(String api  : apis){
							System.out.println(api);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
		    }
		} 
	 }
}
