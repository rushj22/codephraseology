package unusedImport;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.*;
public class Main {
	
	public static void main(String[] args){
		String filePath = "G:\\Codes\\Java\\MyFirst.java";
		ExecuteCommand obj = new ExecuteCommand();
		String command = "srcml " + filePath;
		String output = obj.executeCommand(command);
<<<<<<< Updated upstream
		
	    
=======
//		System.out.println(output);
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);
		ArrayList<ArrayList<String>> splitStarStore = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> importsDeclarations = new ArrayList<ArrayList<String>>();
		ArrayList<String> splitNonStarStore = new ArrayList<String>();
		Map<String, Integer> codeValues = new HashMap<>();	
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
    			codeValues.put(matcher.group(1).split("<name>")[1].trim(),0);
    		}
	    	else
	    	//	System.out.println(matcher.group(1));
	    		codeValues.put(matcher.group(1),0);	    		
	    }
	    
	    /*
	    System.out.println("hi");
	    for (Map.Entry<String, Integer> entry : code_values.entrySet())
	        System.out.println("Key = " + entry.getKey());*/
	    
	    int count=0;
	    int flag;
	    for(ArrayList<String> inner : splitStarStore) {
	        for(String s : inner) {
	        	for (Map.Entry<String, Integer> entry1 : codeValues.entrySet())
	    		{
	    			if(s.equals(entry1.getKey()))
	    			{
	    				ArrayList<String> inner1 = new ArrayList<String>();
						inner1.add(s);
	    				importsDeclarations.add(inner1);
	    			}
	    		}
	        }
	    }
	    
	    for(Iterator iterator = splitNonStarStore.iterator(); iterator.hasNext();)
	    {
	    	String s=(String) iterator.next();
	    	for (Map.Entry<String, Integer> entry1 : codeValues.entrySet())
    		{
    			if(s.equals(entry1.getKey()))
    			{
    				ArrayList<String> inner1 = new ArrayList<String>();
					inner1.add(s);
    				importsDeclarations.add(inner1);
    			}
    		}
	    }

	    output=output.split("<function>")[1].split("</function>")[0];
		Pattern declPattern = Pattern.compile("<decl>(.*?)</decl>");
	    Matcher declMatcher = declPattern.matcher(output);	    
	    Pattern namePattern = Pattern.compile("<name>(.*?)</name>");
	    Pattern argumentPattern = Pattern.compile("<argument>(.*?)</argument>");
	    Matcher nameMatcher;
	    Matcher argumentMatcher;
	    ArrayList<ArrayList<String>> importsDeclarationsNew = new ArrayList<ArrayList<String>>();
	    
	    while (declMatcher.find()) {
	    	argumentMatcher=argumentPattern.matcher(declMatcher.group(1));
	    	String string=declMatcher.group(1);
	    	while(argumentMatcher.find())
	    	{
	    		string = string.replace(argumentMatcher.group(1),"");
	    	}
	    	nameMatcher = namePattern.matcher(string);
	    	for(ArrayList<String> inner : importsDeclarations) {
	    		ArrayList<String> inner1 = new ArrayList<String>();
	    		for(String s : inner) {
	    			if(declMatcher.group(1).contains(s))
	    			{
	    				inner1.add(s);
	    				while(nameMatcher.find())
	    				{
	    					if(nameMatcher.group(1).split("<name>")[0].trim().equals("")) {
				    			if(nameMatcher.group(1).split("<name>")[1].trim().equals(s))
				    			{}
				    			else
					    			inner.add(nameMatcher.group(1).split("<name>")[1].trim());	
	    					}
						    else
							{
				    			if(nameMatcher.group(1).equals(s))
				    			{}
				    			else
				    				inner1.add(nameMatcher.group(1));	
							}
	    				}
	    			}
	    		}
	    		importsDeclarationsNew.add(inner1);
	    	}
	    }  	
	    Pattern expPattern = Pattern.compile("<expr>(.*?)</expr>");
	    Matcher expMatcher = expPattern.matcher(output);	    
	    Pattern namePattern1 = Pattern.compile("<name>(.*?)</name>");
	    Matcher nameMatcher1;
	    Map<String, Integer> importsDeclarationsExp = new HashMap<>();
	    
	    while (expMatcher.find()) {
	    	nameMatcher1 = namePattern1.matcher(expMatcher.group(1));
			while(nameMatcher1.find())
			{
				if(nameMatcher1.group(1).split("<name>")[0].trim().equals("")) {
		    			importsDeclarationsExp.put(nameMatcher1.group(1).split("<name>")[1].trim(),0);
					}
				else
				{
		    			importsDeclarationsExp.put(nameMatcher1.group(1),0);	
				}		    	    			    		    							
			}
	    }
	    count=0;
	    Map<String, Integer> codeValuesNew = new HashMap<>();	
	    for(ArrayList<String> inner : importsDeclarationsNew) {
	        for(String s : inner)
	        {
//	        	System.out.println("to "+s);
	        	for (Map.Entry<String, Integer> entry1 : importsDeclarationsExp.entrySet())
	    		{
	    			if(s.equals(entry1.getKey()))
	    			{
	    				if(s.equals(importsDeclarationsNew.get(count).get(0)))
	    				{}
	    				else
	    				{
		    				codeValuesNew.put(importsDeclarationsNew.get(count).get(0),0);
	    				}	    				
	    			}
	    		}
	        }
	        count++;
	    }
	    /*
	    for (Map.Entry<String, Integer> entry1 : codeValuesNew.entrySet())
		{
	    	System.out.println("LA "+entry1.getKey());
		}*/
	    
	    count=0;
	    ArrayList<String> usedImports = new ArrayList<String>();
	    for(ArrayList<String> inner : splitStarStore) {
	    	flag=0;
	        for(String s : inner) {
	        	for (Map.Entry<String, Integer> entry1 : codeValuesNew.entrySet())
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
	    				if(flag==1)
	    				{
	    					for (Iterator iterator = importsList.iterator(); iterator.hasNext();){
		    					String string = (String) iterator.next();
		    					if(string.contains(splitStarStore.get(count).get(0)) && string.contains("*"))
		    						usedImports.add(string);
	    				}	    				
    				}
    			}
    		}
        }
	        count++;
	    }
	    
	    for(Iterator iterator = splitNonStarStore.iterator(); iterator.hasNext();)
	    {
	    	String s=(String) iterator.next();
	    	for (Map.Entry<String, Integer> entry1 : codeValuesNew.entrySet())
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
	    System.out.println("Unused imports are: ");
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
	    		 System.out.println(string1);
	    	 }
	    }
>>>>>>> Stashed changes
	}
}
