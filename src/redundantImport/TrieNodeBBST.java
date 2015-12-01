ackage Q1.TrieNode;

import java.util.TreeMap;

public class TrieNodeBBST {
	private char nodeVal;
	private boolean isEnd;
	private TreeMap<Character, TrieNodeBBST> children;

	public TrieNodeBBST(char val)
	{
		this.nodeVal = val;
		this.isEnd = false;
		this.children = new TreeMap<>();
	}
	
	public TrieNodeBBST getNextNode(char val)
	{
		return this.children.get(new Character(val));
	}
	public void setNextNode(char val , TrieNodeBBST tr)
	{
		this.children.put(new Character(val), tr);
	}
	
	public void setIsEnd(boolean val) {this.isEnd = val;}
	public boolean isEnd() {return this.isEnd;}	
	public char getVal(){return this.nodeVal;}
	
}
