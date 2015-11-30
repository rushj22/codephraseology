package redundantImport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RedundantImport {
	
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

}
