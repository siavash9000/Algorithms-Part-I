import java.util.HashMap;
import java.util.HashSet;

public class Solver {
	private Stack<Board> solution;
	private Node goal;
	
	public Solver(Board initial) {
		Node originalProblemStart = new Node(initial);
		Node dualProblemStart = new Node(initial.twin());
		originalProblemStart.pathCost=0;
		dualProblemStart.pathCost=0;
		AstarStepper originalSearch = new AstarStepper(originalProblemStart);
		AstarStepper dualSearch = new AstarStepper(dualProblemStart);
		while(originalSearch.searchStepPossible() && dualSearch.searchStepPossible()){
			originalSearch.performSearchStep();
			dualSearch.performSearchStep();
		}
		solution = originalSearch.solution;
	}
	private class AstarStepper{
		private MinPQ<Node> openPriorityQueue = new MinPQ<Node>();
		private HashMap<String, Node> openSet = new HashMap<String, Node>();
		private	HashSet<String> closedList = new HashSet<String>();
		public Stack<Board> solution;
		public boolean solutionFound=false;
		public AstarStepper(Node start) {
			openPriorityQueue.insert(start);
			openSet.put(start.board.toString(), start);
		}
		private void performSearchStep(){
			Node current = openPriorityQueue.delMin();
			if (current.board.isGoal()){
				goal = current; 
				solutionFound=true;
				solution = createSolution();
			}
			else
				closedList.add(current.board.toString());
			expandNode(current);
		}

		public boolean searchStepPossible() {
			return !openPriorityQueue.isEmpty() && !solutionFound;
		}
		private void expandNode(Node node) {
			for (Board neighbour: node.board.neighbors()){
				Node succesor = new Node(neighbour);
				if (closedList.contains(succesor.board.toString()))
					continue;
				Integer tentativeCost = node.pathCost + 1;
				if (openSet.keySet().contains(succesor.board.toString()) && tentativeCost>=succesor.pathCost)
					continue;
				
				Integer newPriority = tentativeCost + succesor.board.manhattan();
				assert(succesor.priority==null || succesor.priority<newPriority);
				succesor.priority = newPriority;
				if (!openSet.keySet().contains(succesor.board.toString())){
					succesor.predecessor = node;
					succesor.pathCost = tentativeCost;
					openPriorityQueue.insert(succesor);
					openSet.put(succesor.board.toString(),succesor);
				}
				else {
					succesor = openSet.get(succesor.board.toString());
					succesor.predecessor = node;
					succesor.pathCost = tentativeCost;
				}
			}
		}
		private Stack<Board> createSolution() {
			Stack<Board> solution = new Stack<Board>();
			Node current = goal;
			while(current!=null){
				solution.push(current.board);
				current=current.predecessor;
			}
			return solution;
		}
	}
	

	public boolean isSolvable(){
		return this.solution!=null;
	}
	public int moves(){
		if (isSolvable())
			return goal.pathCost;
		else
			return -1;
	}

	public Iterable<Board> solution() {
		if(!isSolvable())
			return null;
		return solution;
	}


	private class Node implements Comparable<Node>{
		Board board;
		Integer pathCost = Integer.MAX_VALUE;
		Integer priority;
		Node predecessor;
		Node(Board board){
			this.board = board;
		}
		@Override
		public int compareTo(Node that) {
			return priority.compareTo(that.priority);
		}
	    @Override
	    public boolean equals(Object that){
	    	return board.equals(((Node)that).board);
	    }
	}
	
	public static void main(String[] args) {
	    // create initial board from file
	    In in = new In("8puzzle/puzzle32.txt");
	    //In in = new In("8puzzle/puzzle3x3-unsolvable.txt");
	    
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    Stopwatch watch = new Stopwatch();
	    // solve the puzzle
	    Solver solver = new Solver(initial);
	    System.out.println(watch.elapsedTime());
	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}
}