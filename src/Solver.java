import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
	private MinPQ<Node> openList = new MinPQ<Node>();
	private	HashSet<Node> closedList = new HashSet<Node>();
	private Board goal = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});
	private boolean solvable;
	
	private boolean search(Node start){
		openList.insert(start);
		while (!openList.isEmpty()){
			Node current = openList.delMin();
			if (current.equals(goal))
				return true;
			else
				closedList.add(current);
			expandNode(current);
		}
		return false;
	}

	private void expandNode(Node node) {
		for (Board neighbour: node.board.neighbors()){
			Node succesor = new Node(neighbour,node.move+1);
			if (closedList.contains(succesor))
				continue;
			//Integer tentativeCost = succesor
		}
	}

	public Solver(Board initial) {
		Node start = new Node(initial, 0);
		solvable = search(start);
	}
	/*
              // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable()             // is the initial board solvable?
    public int moves()                      // min number of moves to solve initial board; -1 if no solution
    public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
    public static void main(String[] args)  
*/
	private class Node implements Comparable<Node>{
		private Board board;
		private Integer move;
		private Integer priority;
		private ArrayList<Node> descendants = new ArrayList<Node>();
		private Node(Board board,Integer move){
			this.board = board;
			this.move = move;
		}
		public void addDescendant(Node descendant){
			 descendants.add(descendant);
		}
		@Override
		public int compareTo(Node that) {
			return this.move.compareTo(that.move);
		}
	    @Override
	    public boolean equals(Object y){
	    	return board.equals(y);
	    }
	}
}