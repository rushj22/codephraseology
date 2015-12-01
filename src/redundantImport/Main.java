package redundantImport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.*;
public class Main {
	public static void main(String[] args) throws IOException {
		String filePath = "G:\\Codes\\Java\\MyFirst.java";
		Map<String, Integer> code_values = new HashMap<>();	

		ExecuteCommand obj = new ExecuteCommand();
		String command = "srcml " + filePath;
		String output = obj.executeCommand(command);
		
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);

		Tree tree = new Tree();
		System.out.println("User Import Statements");
		for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
			tree.add(string.split("\\."));
		}
		System.out.println("Tree");
		tree.display();
		
		ArrayList<String> usedList = AnalyzeImports.getUsedList(output);
		ArrayList<String> unusedList = AnalyzeImports.getUnusedList(output);
		
		System.out.println("Used List");
		for (Iterator iterator = usedList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
//		unusedList.add("java.util.ArrayList");
		System.out.println("Unused List");
		for (Iterator iterator = unusedList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
			tree.delete(string.split("\\."));
		}
		System.out.println("Tree after removing Unused Imports");
		tree.display();

		System.out.println();
//		Tree dbTree = DBTree.getDBTree();

//		DBtree.display();

//		tree.compareToDB();
//		tree.display();
		
//		for (Iterator iterator = usedList.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
////			System.out.println(string);
//		}
		
		output=output.split("<class>")[1].split("</class>")[0];
		Pattern pattern = Pattern.compile("<name>(.*?)</name>");
		Matcher matcher = pattern.matcher(output);	    
		while (matcher.find()) {
			if(matcher.group(1).split("<name>")[0].trim().equals("")) {
				code_values.put(matcher.group(1).split("<name>")[1].trim(),0);
			}
			else
				code_values.put(matcher.group(1),0);	    		
		}
		for (Iterator iterator = code_values.keySet().iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
//			System.out.println(string);
			tree.markVisited(string);
		}
		System.out.println("Tree After marking Visited");
		tree.display();
		tree.compareToDB();
//		System.out.println("New Tree");
//		tree.display();
		for (Iterator iterator = code_values.keySet().iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
//			System.out.println(string);
			tree.markVisited(string);
		}
		
		Tree dbTree = DBTree.getDBTree();
		System.out.println("\nAfter Removing redundants");
//		tree.display();
		
		ArrayList<String> redundants = tree.removeRedundantStars();
		for (Iterator iterator = redundants.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			tree.delete((string).split("\\."));
			if(!unusedList.contains(string)) {
				unusedList.add(string);
			}
		}
		tree.display();
		
		System.out.println("\nRedundant Statements: ");
		for (Iterator it = unusedList.iterator(); it.hasNext();) {
			String string = (String) it.next();
			System.out.println(string);
		}
	};
}