package reorderImports;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import common.ExecuteCommand;
import common.ImportsList;

public class Main {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\Lenvo\\Desktop\\testcode.java";
		ExecuteCommand obj = new ExecuteCommand();
		String command = "srcml " + filePath;
		String output = obj.executeCommand(command);
//		System.out.println(output);
		ImportsList obj1 = new ImportsList();
		ArrayList<String> importsList = obj1.ImportsList(output);
		ArrayList<String> hasJavas = new ArrayList<String>();
		ArrayList<String> hasJavax = new ArrayList<String>();
		ArrayList<String> hasOthers = new ArrayList<String>();
		ArrayList<String> importsNotStaticList= new ArrayList<String>();
		ImportsList obj2 = new ImportsList();
		ArrayList<String> importsStaticList = obj2.returnStaticList(output);
		for (Iterator iterator = importsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			int flag=0;
			for (Iterator iterator1 = importsStaticList.iterator(); iterator1.hasNext();) {
				String string1 = (String) iterator1.next();
				if(string.equals(string1))
				{
					flag=1;
				}
			}
			if(flag==0)
			{
				if(string.contains("java"))
					hasJavas.add(string);
				else if(string.contains("javax"))
					hasJavax.add(string);
				else
					hasOthers.add(string);
			}
		}
		Collections.sort(importsStaticList, new Comparator<String>() {
	        @Override
	        public int compare(String  o1, String  o2)
	        {

	            return  o1.compareTo(o2);
	        }
	    });
		Collections.sort(hasJavas, new Comparator<String>() {
	        @Override
	        public int compare(String  o1, String  o2)
	        {

	            return  o1.compareTo(o2);
	        }
	    });
		Collections.sort(hasJavax, new Comparator<String>() {
	        @Override
	        public int compare(String  o1, String  o2)
	        {

	            return  o1.compareTo(o2);
	        }
	    });
		Collections.sort(hasOthers, new Comparator<String>() {
	        @Override
	        public int compare(String  o1, String  o2)
	        {

	            return  o1.compareTo(o2);
	        }
	    });
		System.out.println("Import files in order by rule are:");
		for (Iterator iterator = importsStaticList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			{
				System.out.println("import static "+string);
			}
		}
		for (Iterator iterator = hasJavas.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			{
				System.out.println("import "+string);
			}
		}
		for (Iterator iterator = hasJavax.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			{
				System.out.println("import "+string);
			}
		}
		for (Iterator iterator = hasOthers.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			{
				System.out.println("import "+string);
			}
		}
	}

}
