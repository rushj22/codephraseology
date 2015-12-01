package redundantImport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.*;
public class Main {
	public static void main(String[] args) {
		String filePath = "G:\\Codes\\Java\\MyFirst.java";
		Map<String, Integer> code_values = new HashMap<>();	

		ExecuteCommand obj = new ExecuteCommand();
		String command = "srcml " + filePath;
		String output = obj.executeCommand(command);
//		System.out.println(output);
		
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);

		Tree tree = new Tree();
		
		for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
			tree.add(string.split("\\."));
			tree.display();
		}
		
		output = output.split("<class>")[1].split("</class>")[0];
		Pattern pattern = Pattern.compile("<name>(.*?)</name>");
		Matcher matcher = pattern.matcher(output);	    
		while (matcher.find()) {
			if(matcher.group(1).split("<name>")[0].trim().equals("")) {
				code_values.put(matcher.group(1).split("<name>")[1].trim(),0);
			}
			else
				code_values.put(matcher.group(1),0);	    		
		}
		
		System.out.println(code_values.toString());
	}
}