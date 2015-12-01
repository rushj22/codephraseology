package redundantImport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class Tree {

	private Node root;
	
	public Tree() {
		this.root = new Node("$");
	}
	
	public void add(String[] string) {
		if(string.length == 0) {
			return;
		}
		Node current = this.root;
		Node crawlTr;
		boolean flagForStar = false;
		for(int i = 0 ; i < string.length ; i++) {
			crawlTr = current.getNextNode(string[i]);
			if(i < string.length - 1 && string[i+1].equals("*")) {
//				System.out.println("Bleh");
				crawlTr.setStar(true);
				flagForStar = true;
				break;
			}
			if(crawlTr == null) {
				crawlTr = new Node(string[i]);		//Create new node
				current.setNextNode(string[i], crawlTr);
//				System.out.println(string[i]);
			}
			current = crawlTr;
		}
		if(!flagForStar) {current.setEnd(true);}
	}
	
	public void display() {
		Node current = this.root;
		Queue<Node> tempQueue = new LinkedList<Node>();
		tempQueue.add(current);
		while(!tempQueue.isEmpty()) {
			current = tempQueue.remove();
			System.out.print(current.getNodeVal());
			if(current.isStar()) {
				System.out.print("Star");
			}
			if(current.isEnd()) {
				System.out.print("End");
			}
			TreeMap<String, Node> children = current.getChildren();
			for (Iterator iterator = children.values().iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				tempQueue.add(node);
			}
		}
		System.out.println();
	}
}
