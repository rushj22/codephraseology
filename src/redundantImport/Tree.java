package redundantImport;

import java.util.LinkedList;
import java.util.Queue;

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
		for(int i = 0 ; i < string.length ; i++) {
			crawlTr = current.getNextNode(string[i]);
			if(crawlTr == null) {
				if(string[i].equals("*")) {
					current.setStar(true);
				} else {
					crawlTr = new Node(string[i]);		//Create new node
					current.setNextNode(string[i], crawlTr);
				}
			}
			current = crawlTr;
		}
		current.setEnd(true);
	}
	
	public void display() {
		Node current = this.root;
		Queue<Node> tempQueue = new LinkedList<Node>();
		tempQueue.add(current);
		while(!tempQueue.isEmpty()) {
			current = tempQueue.remove();
			System.out.print(current.getNodeVal());
			for(int i = 0; i < current.getChildren().size(); i++) {
				tempQueue.add(current.getChildren().get(i));
			}
		}
		
	}
}
