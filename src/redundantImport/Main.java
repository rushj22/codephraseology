package redundantImport;
import common.*;
public class Main {
	public static void main(String[] args) {
		String filePath = "G:\\Codes\\Java\\MyFirst.java";
		
		ExecuteCommand obj = new ExecuteCommand();
		String command = "srcml " + filePath;
		String output = obj.executeCommand(command);
//		System.out.println(output);
		
		ImportsList obj1 = new ImportsList();
		obj1.ImportsList(output);
	}
}
