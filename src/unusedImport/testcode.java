package unusedImport;

import java.util.ArrayList;

public class testcode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	String s=".aru.shi.j;";
		String[] temp = s.split(";");
		int count=0;
		for (String si : temp)
		{
			System.out.println(si);
			System.out.println(temp[count]);
			count++;
		}*/
		ArrayList<ArrayList<String>> splitStore = new ArrayList<ArrayList<String>>();
		ArrayList<String> inner = new ArrayList<String>();
		inner.add("1");
		inner.add("2");
		splitStore.add(inner);
		inner = new ArrayList<String>();
		inner.add("3");
		inner.add("4");
		splitStore.add(inner);
		
		for(ArrayList<String> inner1 : splitStore) {
	        for(String s : inner1)
	        {
	        	System.out.println(s);
	        }
	    }
	}

}
