package redundantImport;

import java.util.TreeMap;

public class Node {

	private String nodeVal;
	private boolean isEnd;
	private boolean isStar;
	private TreeMap<String, Node> children;

	public Node(String val) {
		this.nodeVal = val;
		this.isEnd = false;
		this.isStar = false;
		this.children = new TreeMap<>();
	}
	
	public Node getNextNode(String val) {
		return this.children.get(val);
	}
	
	public void setNextNode(String str , Node tr) {
		this.children.put(str, tr);
	}

	public String getNodeVal() {
		return nodeVal;
	}

	public void setNodeVal(String nodeVal) {
		this.nodeVal = nodeVal;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean isStar() {
		return isStar;
	}

	public void setStar(boolean isStar) {
		this.isStar = isStar;
	}

	public TreeMap<String, Node> getChildren() {
		return children;
	}

	public void setChildren(TreeMap<String, Node> children) {
		this.children = children;
	}

}
