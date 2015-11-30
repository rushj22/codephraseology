package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportsList {
	public static void ImportsList(String output) {
		Pattern importPattern = Pattern.compile("<import>(.*?)</import>");
	    Matcher importMatcher = importPattern.matcher(output);
	    
	    Pattern namePattern = Pattern.compile("<name>(.*?)</name>");
	    Pattern strayNamePattern = Pattern.compile("<name>(.*?)");
	    Matcher nameMatcher;
	    String importName;
	    ArrayList<String> results;
	    while (importMatcher.find()) {
	    	nameMatcher = namePattern.matcher(importMatcher.group(1));
	        
	    	while(nameMatcher.find()) {
	        	System.out.println(nameMatcher.group(1));
	        }
	    }
	}
}
