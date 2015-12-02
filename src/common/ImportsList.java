package common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportsList {
	public static ArrayList<String> ImportsList(String output) {
		Pattern importPattern = Pattern.compile("<import>(.*?)</import>");
	    Matcher importMatcher = importPattern.matcher(output);	    
	    Pattern namePattern = Pattern.compile("<name>(.*?)</name>");
	    Matcher nameMatcher;
	    String importName;
	    ArrayList<String> results = new ArrayList<String>();
	    
	    while (importMatcher.find()) {
	    	nameMatcher = namePattern.matcher(importMatcher.group(1));
	    	importName = "";
	    	while(nameMatcher.find()) {
		//    	System.out.println("hi "+importMatcher.group(1));
//	    		System.out.println(nameMatcher.group(1));
				if(nameMatcher.group(1).split("<name>")[0].trim().equals("")) {
	    			importName += nameMatcher.group(1).split("<name>")[1].trim();
	    		}
	    		else {
	    			importName += ".";
	    			importName += nameMatcher.group(1).trim();
	    		}
	        }
			results.add(importName);
	    }
	    /*for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}*/
	    return results;
	}
	public static ArrayList<String> returnStaticList(String output)
	{
		Pattern importPattern = Pattern.compile("<import>(.*?)</import>");
	    Matcher importMatcher = importPattern.matcher(output);	    
	    Pattern namePattern = Pattern.compile("<name>(.*?)</name>");
	    Matcher nameMatcher;
	    String importName;
	    ArrayList<String> results = new ArrayList<String>();
	    
	    while (importMatcher.find()) {
	    	int isStatic=0;
	    	nameMatcher = namePattern.matcher(importMatcher.group(1));
	    	importName = "";
//			System.out.println(importMatcher.group(1));
	    	if(importMatcher.group(1).contains("static"))
	    		isStatic=1;
	    	while(nameMatcher.find()) {
//	    		System.out.println(nameMatcher.group(1));
				if(nameMatcher.group(1).split("<name>")[0].trim().equals("")) {
	    			importName += nameMatcher.group(1).split("<name>")[1].trim();
	    		}
	    		else {
	    			importName += ".";
	    			importName += nameMatcher.group(1).trim();
	    		}
	        }
			if(isStatic==1)
			{
				results.add(importName);
			}
	    }
	    /*for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}*/
	    return results;
	}
}
