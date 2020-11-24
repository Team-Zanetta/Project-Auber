package com.zanetta.auber;

public class Node {
	public int i, j;
	public int heuristic_cost;
	public int finalCost;
	public boolean solution;
	public Node parent;
	
	public Node(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
