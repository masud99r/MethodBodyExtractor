package MethodExtraction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.File;

public class Main {
	public static String process_text(String content){
		String processed_text = content.replaceAll("\n", " ");
		processed_text = processed_text.replaceAll("\r", " ");
		processed_text = processed_text.replaceAll("\t", " ");
		return processed_text;
	}
	public static void main(String args[]) throws IOException {
		
		if(args.length > 0) {
		//if(true) {
	        String infile = args[0];
	        //String infile = "tests/org.eclipse.jdt.junitSlasHEclipse JUnitSlasHorgSlasHeclipseSlasHjdtSlasHjunitSlasHinternalSlasHJUnitUIPlugin.java";
	        //String infile = "K:/Masud/EclipseProjects/MethodBodyExtractor/tests/org.eclipse.jdt.junitSlasHEclipse JUnitSlasHorgSlasHeclipseSlasHjdtSlasHjunitSlasHinternalSlasHJUnitUIPlugin.java";
	        
			JavaMethodExtractor methodExtractor = new JavaMethodExtractor(infile);
			methodExtractor.extract();
			try {
				HashMap<String, String> methodBodies = methodExtractor.getMethodBodies(); // methodSignature => methodBody
				HashMap<String, ArrayList<String>> apiCalls = methodExtractor.getAPICalls(); //methodSignature => ArrayList of API Calls

				Set<String> methodSigs = methodBodies.keySet();
				int method_count = 0;
				for (String methodSig: methodSigs){
					method_count ++;
					//System.out.println("\n\nSignature:" + methodSig);
					
					//System.out.println("MethodBody: "+methodBodies.get(methodSig));
					ArrayList<String> apis = apiCalls.get(methodSig);
					String apiList = "";
					for(String api  : apis){
						//System.out.println(api);
						String[] api_part = api.split("\\.");
						String api_name = api;
						
						/*if (api_part.length>0){
							
							api_name = api_part[api_part.length - 1]; // consider last invocation as actual API method call 
							//System.out.println(api_part.length);
							//System.out.println(api_name);
						}
						api_name = api_name.replaceAll("\\(.*\\)", ""); //remove anything within parenthesis w/ parenthesis
						api_name = api_name.trim();
						*/
						apiList = apiList +" "+api_name;
					}
					if (apiList ==""){
							apiList = "NONE";
					}
					String method_name = methodSig.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", ""); //remove comment
					method_name = method_name.replace("@Override", "");
					method_name = method_name.replaceAll("\\(.*\\)", ""); //remove anything within parenthesis w/ parenthesis
					method_name = method_count+"_"+method_name.trim();
							
					System.out.println(process_text(method_name)+"\t"+process_text(methodSig+" "+methodBodies.get(methodSig))+"\t"+apiList);

				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Please use correct format");
		}
		
	}
}
