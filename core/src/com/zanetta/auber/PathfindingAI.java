package com.zanetta.auber;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PathfindingAI {
	
	
	
	public static final int DIAGONAL_COST = 14;
	public static final int V_H_COST = 10;
	private Node[][] grid;
	private PriorityQueue<Node> openNodes;
	private boolean [][] closedNodes;
	private int startI, startJ;
	private int endI, endJ;
	
	public PathfindingAI(int height, int width, int si, int sj, int ei, int ej, int [][] blocks) {
		grid = new Node[width][height];
		closedNodes = new boolean[width][height];
		openNodes = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.finalCost < n2.finalCost ? -1 : n1.finalCost > n2.finalCost ? 1 : 0;
			}
		});
		
		startNode(si, sj);
		endNode(ei, ej);
				
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Node(i, j);
				grid[i][j].heuristic_cost = Math.abs(i - endI) + Math.abs(j - endJ);
				grid[i][j].solution = false;
			}
		}
		
		grid[startI][startJ].finalCost = 0;
		
		for (int i = 0; i < blocks.length; i++) {
			addBlockOnNode(blocks[i][0], blocks[i][1]);
		}
		
	}	
		
	public void addBlockOnNode(int i, int j) {
		grid[i][j] = null;
	}
	
	public void startNode(int i, int j) {
		startI = i;
		startJ = j;
	}
	
	public void endNode(int i, int j) {
		endI = i;
		endJ = j;
	}

	public void updateCostIfNeeded(Node current, Node t, int cost) {
		if (t == null || closedNodes[t.i][t.j])
			return;
		
		int tFinalCost = t.heuristic_cost + cost;
		boolean isOpen = openNodes.contains(t);
		
		if (!isOpen || tFinalCost < t.finalCost) {
			t.finalCost = tFinalCost;
			t.parent = current;
			
			if (!isOpen)
				openNodes.add(t);
		}
	}
	
	public void process() {
		openNodes.add(grid[startI][startJ]);
		Node current;
		
		while (true) {
			current = openNodes.poll();
			
			if (current == null)
				break;
			
			closedNodes[current.i][current.j]= true;
			
			if (current.equals(grid[endI][endJ]))
				return;
			
			Node t;
			
			if (current.i - 1 >= 0) {
				t = grid[current.i - 1][current.j];
				updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
				
				if (current.j - 1 >= 0) {
					t = grid[current.i - 1][current.j - 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
				}
				
				if (current.j + 1 < grid[0].length) {
					t = grid[current.i - 1][current.j + 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
				}
			}
			
			if (current.j - 1 >= 0) {
				t = grid[current.i][current.j - 1];
				updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
			}
			
			if (current.j + 1 < grid[0].length) {
				t = grid[current.i][current.j + 1];
				updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
			}
			
			if (current.i + 1 < grid.length) {
				t = grid[current.i + 1][current.j];
				updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
				
				if (current.j - 1 >= 0) {
					t = grid[current.i + 1][current.j - 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
				}
				
				if (current.j + 1 < grid[0].length) {
					t = grid[current.i + 1][current.j + 1];
					updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
				}
			}
		}
	}
}
