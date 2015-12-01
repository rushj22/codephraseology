package common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.ImportsList;

public class AnalyzeImports {

	// Input is the String of srcml
	public static ArrayList<String> getUsedList(String output) throws IOException {
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);
		ArrayList<ArrayList<String>> splitStarStore = new ArrayList<ArrayList<String>>();
		ArrayList<String> splitNonStarStore = new ArrayList<String>();
		Map<String, Integer> import_values = new HashMap<>();
		Map<String, Integer> code_values = new HashMap<>();	
		
		for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if(string.contains("*"))
			{
				String[] temp = string.split("\\.");
				//removed java
				int count=0;
				for (String s : temp)
				{
					if(s.contains("*"))
					{
						ArrayList<String> inner = new ArrayList<String>();
						inner.add(temp[count-1]);
						FileInputStream fstream1 = new FileInputStream("importPackages.txt");
						BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
						String strLine;
						
						while ((strLine = br1.readLine()) != null)   
						{	
							String string1 = temp[count-1];
							
							if(strLine.contains(string1))
							{
								
								String[] temp1 = strLine.split(string1);
								String[] temp2 = temp1[1].split(";");
							
								String[] temp3 = temp2[0].split("\\.");
								int count1=0;
								for (String s1 : temp3)
								{
									if(count1!=0)
									{								
										inner.add(s1);
									}
									count1++;
								}	
								
							}
						}
						splitStarStore.add(inner);
				/*		for(ArrayList<String> inner1 : splitStore) {
					        for(String s1 : inner1)
					        {
					        	System.out.println("hey"+s1);
					        }
					    }*/
					}
				count++;
				}
			}
			else
			{
				String[] temp = string.split("\\.");
				int count=0;
				String temp1=null;
				for (String s : temp)
				{
						temp1 = s;
				}
			   splitNonStarStore.add(temp1);
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
	    
	    int count=0;
	    int flag;
	    ArrayList<String> usedImports = new ArrayList<String>();
	    for(ArrayList<String> inner : splitStarStore) {
	    	flag=0;
	        for(String s : inner) {
	        	for (Map.Entry<String, Integer> entry1 : code_values.entrySet())
	    		{
	    			if(s.equals(entry1.getKey()))
	    			{
	    				for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
	    					String string = (String) iterator.next();
	    					String[] temp = string.split("\\.");
    						for (String s1 : temp)
    						{
    							if(s1.equals(s))
    							{
    								flag=1;
    								break;
    							}
    						}
    						if(flag==1)
    							break;
    							
	    				}
	    				for (Iterator iterator = importsList.iterator(); iterator.hasNext();){
	    					String string = (String) iterator.next();
	    					if(string.contains(splitStarStore.get(count).get(0)) && string.contains("*"))
	    						usedImports.add(string);
	    				}
	    			}
	    		}
	        }
	        count++;
	    }
	    
	    for(Iterator iterator = splitNonStarStore.iterator(); iterator.hasNext();)
	    {
	    	String s=(String) iterator.next();
	    	for (Map.Entry<String, Integer> entry1 : code_values.entrySet())
    		{
    			if(s.equals(entry1.getKey()))
    			{
    				for (Iterator iterator1 = importsList.iterator(); iterator1.hasNext();) {
    					String string = (String) iterator1.next();
    					String[] temp = string.split("\\.");
						for (String s1 : temp)
						{
							if(s1.equals(s))
							{
								usedImports.add(string);
								break;
							}
						}	    					
    				}
    			}
    		}
	    }
	    return usedImports;
	}
	
	public static ArrayList<String> getUnusedList(String output) throws IOException {
		ArrayList<String> usedImports = getUsedList(output);
		int flag = 0;
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);
		
		ArrayList<String> unusedList = new ArrayList<String>();
		for (Iterator iterator = importsList.iterator(); iterator.hasNext();) 
	    { 
	    	 flag=0;
	    	 String string1 = (String) iterator.next();
	    	 for (Iterator iterator1 = usedImports.iterator(); iterator1.hasNext();) 
	    	 {	    		
	    		 String string2 = (String) iterator1.next();
	    		 if(string1.equals(string2))
	    		 {
	    			 flag=1;
	    			 break;
	    		 }
	    	 }
	    	 if(flag==0)
	    	 {
	    		 unusedList.add(string1);
	    	 }
	    }
		return unusedList; 
	}
}
