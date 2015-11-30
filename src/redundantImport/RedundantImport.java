package redundantImport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RedundantImport {
	public static void main(String[] args) {
		String filePath = "G:\\Codes\\Java\\MyFirst.java";
		
		RedundantImport obj = new RedundantImport();
		String command = "srcml " + filePath;
		String output = obj.executeCommand(command);
		System.out.println(output);
		
	}
	
	private static ArrayList<String> getImports(String output) {
		ArrayList<String> imports = null;
		String[] bleh = output.split("</import>");
		for (int i = 0; i < bleh.length; i++) {
			String string = bleh[i];
//			System.out.println(i + "\n" + bleh[i]);
		}
		System.out.println(bleh.toString());
		return imports;
	}
	
	private String executeCommand(String command) {
		
		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();
	}
}
