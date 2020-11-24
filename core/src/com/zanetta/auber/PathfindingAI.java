package com.zanetta.auber;

public class Node {
	public int i, j;
	public int heuristic_cost;
	public int final_cost;
	public boolean solution;
	
	public Node(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

public class PathFinding {
	
	
	
	public static final int DIAGONAL_COST = 14;
	public static final int V_H_COST = 10;
	private Node[][] grid;
	private PriorityQueue<Node> openNodes;
	private boolean [][] closedNodes;
	private int startI, startJ;
	private int endI, endJ;
	
	public PathFinding(int height, int width, int si, int sj, int ei, int ej, int [][] blocks) {
		grid = new Node[width][height];
		closedNodes = new boolean[width][height];
		openNodes = PriorityQueue<Node>((Node n1, Node n2) -> {
			return n1.final_cost < n2.final_cost ? -1 : n1.final_cost > n2.final_cost ? 1 : 0;
		});
		
		startNode(si, sj);
		endNode(ei, ej);
				
		for (i = 0; i < grid.length; i++) {
			for (j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Node(i, j);
				grid[i][j].heuristic_cost = Math.abs(i - endI) + Math.abs(j - endJ));
				grid[i][j].solution = false;
			}
		}
		
		grid[startI][startJ].final_cost = 0;
		
		for (i = 0; i < blocks.length; i++) {
			addBlockOnNode(blocks[i][0], blocks[i][1]);
		}
		
	}	
		
		public void addBlockOnNode(int i, int j) {
			grid[i][j] = null;
		}
		
		public void startNode(int i, intj) {
			startI = i;
			startJ = j;
		}
		
		public void endNode(int i, int j) {
			endI = i;
			endJ = j;
		}
	
	
}

