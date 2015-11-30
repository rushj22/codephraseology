package redundantImport;
import java.util.ArrayList;
import java.util.Iterator;

import common.*;
public class Main {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\Lenvo\\Desktop\\testcode.java";
		
		ExecuteCommand obj = new ExecuteCommand();
		String command = "srcml " + filePath;
		String output = obj.executeCommand(command);
//		System.out.println(output);
		
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);
		
		for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
		
	}
}
