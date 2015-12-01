package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import redundantImport.Tree;

public class DBTree {

	public static Tree getDBTree() {
		Tree tree = new Tree();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("importPackages.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
//				System.out.println(sCurrentLine);
				String temp = sCurrentLine.split(" ")[1];
				String[] bleh = ((String)temp.subSequence(0, temp.length() - 1)).split("\\.");
				/*for (int i = 0; i < bleh.length; i++) {
					String string = bleh[i];
					System.out.println(string);
				}*/
				tree.add(bleh);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return tree;
	}
}
