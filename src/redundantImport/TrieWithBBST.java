package redundantImport;

public class TrieWithBBST {

	private TrieNodeBBST root;
	public TrieWithBBST()
	{
		this.root = new TrieNodeBBST("$");
	}
	
	public boolean contains(String str)
	{	
		if(str.length() == 0 )		//Empty String
			return true;
		
		TrieNodeBBST tr = this.root;
		String current;
		String nextPart = "";
		String[] parts = str.split("\\.");
		for(int i = 0 ; i< str.length() ; i++)
		{
			
			current = parts[0];
			for(int j = 1; j < parts.length; j++) {
				nextPart += parts[j];
				nextPart += ".";
			}
			nextPart = (String) nextPart.subSequence(0, nextPart.length());
			tr = tr.getNextNode(nextPart);
			if(tr == null)
				return false;			//Word not contained
		}
		if(tr.isEnd())
			return true;				//Word Exists
		return false;					//Proper prefix but word not contained in trie
	}
	
	public void insert(String str)
	{
		if(str.length() == 0 )		//Empty String
			return;
		
		TrieNodeBBST tr = this.root;
		String current;
		String nextPart = "";
		String[] parts = str.split("\\.");
		for(int i = 0 ; i< str.length() ; i++)
		{
			current = parts[0];
			for(int j = 1; j < parts.length; j++) {
				nextPart += parts[j];
				nextPart += ".";
			}
			nextPart = (String) nextPart.subSequence(0, nextPart.length());
			TrieNodeBBST crawlTr = tr.getNextNode(nextPart);
			if(crawlTr == null)
			{
				crawlTr = new TrieNodeBBST(nextPart);		//Create new node
				tr.setNextNode(nextPart, crawlTr);			
			}
			tr = crawlTr;
		}
		if(tr.equals("*")) {
			tr.setStar(true);
		}
		tr.setIsEnd(true);
	}

	
	
	public static void main(String[] args) {
		System.out.println("Trie With BBST : \n");

		String str = "java.util.ArrayList java.util.Priority java.util.*";
		String []strArray = str.split(" ");
		System.out.println("Inserting Words :");
		System.out.println(str + "\n");
		
		TrieWithBBST trie = new TrieWithBBST();
		for(int i = 0 ; i< strArray.length ; i++)
			trie.insert(strArray[i]);
		
		System.out.println("Checking Words : ");
		for(int i = 0 ; i< strArray.length ; i++)
			if(trie.contains(strArray[i]) == false)
				System.out.println("Falied at " + strArray[i]);
		System.out.println("Passed\n");
		
		System.out.println("Checking Prefixes");
		System.out.println("hel (hello): " + trie.contains("java"));
		System.out.println("saur (saurabh) : " + trie.contains("java.util"));
		System.out.println("stud (student): " + trie.contains("java.util.ArrayList"));	
	}

}
