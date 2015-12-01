package redundantImport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

import common.DBTree;

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
				if(crawlTr == null) {
					crawlTr = new Node(string[i]);
					current.setNextNode(string[i], crawlTr);
					crawlTr.setParent(current);
				}
				crawlTr.setStar(true);
				flagForStar = true;
				break;
			}
			if(crawlTr == null) {
				crawlTr = new Node(string[i]);		//Create new node
				current.setNextNode(string[i], crawlTr);
				crawlTr.setParent(current);
//				System.out.println(string[i]);
			}
			current = crawlTr;
		}
		if(!flagForStar) {current.setEnd(true);}
	}
	
	public void delete(String[] string) {
		if(string.length == 0) {
			return;
		}
		Node current = this.root;
		Node crawlTr;
		for(int i = 0; i< string.length; i++) {
			crawlTr = current.getNextNode(string[i]);
//			System.out.println("crawlTr: " + crawlTr.getNodeVal());
			if(crawlTr == null) {
//				System.out.println("Doesn't exist");
				return;
			} else {
				current = crawlTr;
			}
		}
		Node parent = current.getParent();
//		System.out.println("Parent: " + parent.getNodeVal());
//		System.out.println("Current: " + current.getNodeVal());
		
		while(!current.getNodeVal().equals("$")) {
			TreeMap<String, Node> children = parent.getChildren();
			children.remove(current.getNodeVal());
			parent.setChildren(children);
			if(children.size() == 0 && !parent.isStar()) {
				current = parent;
				parent = current.getParent();
			} else {
				break;
			}
		}
	}
	
	public Node findNode(String s) {
		Node current = this.root;
		Node crawlTr;
		Queue<Node> tempQueue = new LinkedList<Node>();
		tempQueue.add(current);
		while(!tempQueue.isEmpty()) {
			current = tempQueue.remove();
			if(current.getNodeVal().equals(s)) {
				return current;
			}
			TreeMap<String, Node> children = current.getChildren();
			for (Iterator iterator = children.values().iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				tempQueue.add(node);
			}
		}
		return null;		
	}
	
	public void compareToDB() {
		Tree dBtree = DBTree.getDBTree();
		Node current = this.root;
		Node crawlTr;
		Queue<Node> tempQueue = new LinkedList<Node>();
		tempQueue.add(current);
		while(!tempQueue.isEmpty()) {
			current = tempQueue.remove();
			if(current.isStar()) {
				Node temp = dBtree.findNode(current.getNodeVal());
				if(temp!= null){
//					current.setChildren(temp.getChildren());
					TreeMap<String, Node> newChildren = new TreeMap<>();
					for (Iterator it = temp.getChildren().values().iterator(); it
							.hasNext();) {
						Node node = (Node) it.next();
						if(current.getChildren().containsValue(node)){
							newChildren.put(current.getChildren().get(node.getNodeVal()).getNodeVal(), current.getChildren().get(node.getNodeVal()));
							node.setParent(current);
						} else {
							newChildren.put(node.getNodeVal(), node);
							node.setParent(current);
						}
					}
					current.setChildren(newChildren);
				}
			}
			TreeMap<String, Node> children = current.getChildren();
			for (Iterator iterator = children.values().iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				tempQueue.add(node);
			}
		}
	}
	
	public Node markVisited(String s) {
		Node current = this.findNode(s);
		if(current!= null) {
			current.setVisited(true);
			return current;
		}
		return null;
	}
	
	public boolean allChildrenVisited(Node current) {
		TreeMap<String, Node> children = current.getChildren();
		boolean flag = true;
		for (Iterator it = children.values().iterator(); it.hasNext();) {
			Node node = (Node) it.next();
			if(!node.isVisited()) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public String buildString(String s) {
		Node current = this.findNode(s);
		String str = "";
		if(current!=null){
//			System.out.println(current.getNodeVal());
			Node parent = current.getParent();
			while(!current.getNodeVal().equals("$")) {
				parent = current.getParent();
				str = current.getNodeVal() + "." + str;
				current = parent;
//				System.out.println(str);
//				System.out.println("current: " + current.getNodeVal());
//				System.out.println(current.getNodeVal().equals("$"));
			}
			str = (String) str.subSequence(0, str.length() - 1);
		}
		return str;
	}
	
	public ArrayList<String> removeRedundantStars() {
		ArrayList<String> redundants = new ArrayList<String>();
		Node current = this.root;
		Node crawlTr;
		Queue<Node> tempQueue = new LinkedList<Node>();
		tempQueue.add(current);
		while(!tempQueue.isEmpty()) {
			current = tempQueue.remove();
			if(current.isStar()) {
				TreeMap<String, Node> children = current.getChildren();
				if(allChildrenVisited(current)) {
					for (Iterator it = children.values().iterator(); it
							.hasNext();) {
						Node node = (Node) it.next();
						System.out.println("Node: " + node.getNodeVal());
						String temp = buildString(node.getNodeVal());
						redundants.add(temp);
//						delete(buildString(temp).split("\\."));
					}
				} else {
					for (Iterator it = children.values().iterator(); it
							.hasNext();) {
						Node node = (Node) it.next();
						if(!node.isVisited()){
							redundants.add(buildString(node.getNodeVal()));
//							delete(buildString(temp).split("\\."));
						}
					}
					current.setStar(false);
					redundants.add(buildString(current.getNodeVal()) + ".*");
				}
			}
			TreeMap<String, Node> children = current.getChildren();
			for (Iterator iterator = children.values().iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				tempQueue.add(node);
			}
		}
		return redundants;
	}
	
	public void display() {
		Node current = this.root;
		Queue<Node> tempQueue = new LinkedList<Node>();
		tempQueue.add(current);
		while(!tempQueue.isEmpty()) {
			current = tempQueue.remove();
			if(current.getParent()!= null) {
				System.out.print(" <- ");
			}
			System.out.print(current.getNodeVal());
			if(current.isStar()) {
				System.out.print("Star ");
			}
			if(current.isEnd()) {
				System.out.print("End ");
			}
			if(current.isVisited()) {
				System.out.print("V ");
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
