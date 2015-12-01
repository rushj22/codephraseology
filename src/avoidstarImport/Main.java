package avoidstarImport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.*;
public class Main {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\Lenvo\\Desktop\\testcode.java";
		
		ExecuteCommand obj = new ExecuteCommand();
		String command = "srcml " + filePath;
		String output = obj.executeCommand(command);
		System.out.println(output);
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);
		Map<String, Integer> import_values = new HashMap<>();
		Map<String, Integer> code_values = new HashMap<>();
        
		int flag=0;
		for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String[] temp = string.split("\\.");
			for (String s : temp)
			{
				if(s.equals("*"))
				{
					flag=1;
					System.out.println("* exists");
				}
			}
			
		}
		if(flag==0)
			System.out.println("* doesn't exist");
	}
}
