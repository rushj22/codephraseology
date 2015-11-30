package unusedImport;
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
//		System.out.println(output);
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);
		Map<String, Integer> import_values = new HashMap<>();
		Map<String, Integer> code_values = new HashMap<>();
        
		for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String[] temp = string.split("\\.");
			//removed java
			int flag=0;
			for (String s : temp)
			{
				if(flag==0)
					flag=1;
				else
					import_values.put(s,0);
			}
			
		}
		output=output.split("<class>")[1].split("</class>")[0];
//		System.out.println(output);
		
	    Pattern pattern = Pattern.compile("<name>(.*?)</name>");
	    Matcher matcher = pattern.matcher(output);
	    
	    while (matcher.find()) {
	    	if(matcher.group(1).split("<name>")[0].trim().equals("")) {
    	//		System.out.println(matcher.group(1).split("<name>")[1].trim());
    			code_values.put(matcher.group(1).split("<name>")[1].trim(),0);
    		}
	    	else
	    	//	System.out.println(matcher.group(1));
	    		code_values.put(matcher.group(1),0);
	    		
	    }
	    /*
	    for (Map.Entry<String, Integer> entry : import_values.entrySet())
	        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	    System.out.println("hi");
	    for (Map.Entry<String, Integer> entry : code_values.entrySet())
	        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());*/
	    for (Map.Entry<String, Integer> entry : import_values.entrySet()) {
	    	{
	    		for (Map.Entry<String, Integer> entry1 : code_values.entrySet())
	    		{
	    			if(entry.getKey().equals(entry1.getKey()))
	    			{
	    				for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
	    					String string = (String) iterator.next();
	    				//	System.out.println(string);
	    					String[] temp = string.split("\\.");
    						for (String s : temp)
    						{
    							if(s.equals(entry.getKey()))
    							{
    								System.out.println(string);
    							}
    						}	    					
	    				}
	    			}
	    		}
	    	}
	    }
	}
}
