public class Board {
	
	private int[][] board;
	private int dimension;
	
    public Board(int[][] blocks){
    	if (blocks == null)
    		throw new NullPointerException();
    	this.board = blocks;
    	dimension = blocks.length;
    	for (int[] row: blocks){
    		if (row == null)
    			throw new NullPointerException("null row in given board");
    		if (row.length != dimension)
    			throw new IllegalArgumentException("not a grid: row with wrong dimension");
    	}
    }    
    public int dimension() {
    	return dimension;
    }
    public int hamming() {
    	int wrongPositions = 0;
    	for (int i=0;i<dimension;i++){
    		for (int j=0;j<dimension;j++){
    			int value = board[i][j];
    			if (value != 0 && value != ((i*dimension+j+1)%Math.pow(dimension, 2)))
    				wrongPositions++;
    		}
    	}
    	return wrongPositions;
    }    
    public int manhattan(){
    	int sumOfDistances = 0;
    	for (int i=0;i<this.dimension;i++){
    		for (int j=0;j<this.dimension;j++){
    			int value = this.board[i][j];
    			if (value==0)
    				continue;
    			int goalI = (value==0)?dimension-1:(value-1)/this.dimension;
    			int goalJ = (value==0)?dimension-1:(value-1)%this.dimension;
    			sumOfDistances += Math.abs(goalI-i) + Math.abs(goalJ-j); 
    		}
    	}
    	return sumOfDistances;
    }
    public boolean isGoal(){
    	return hamming() == 0;
    }
    private int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }
    public Board twin() throws Exception{
    	for(int i=0;i<board.length;i++){
    		if (board[i][0]!=0 && board[i][1]!=0){
    			return new Board(cloneAndRotate(this.board,i,0,i,1));
    		}	
    	}
    	throw new Exception("Could not build twin.");
    }
    
    private void changePositions(int[][] board, int i1, int j1, int i2, int j2) {
    	int temp=board[i1][j1];
    	board[i1][j1]=board[i2][j2];
    	board[i2][j2]=temp;
    }
    @Override
    public boolean equals(Object y){
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.dimension() != this.dimension()) return false;
        for (int i=0;i<dimension;i++){
        	for(int j=0;j<dimension;j++){
        		if (board[i][j]!=that.board[i][j])
        			return false;
        	}
        }
        return true;
    }
    private int[][] cloneAndRotate(int[][] source, int i1,int j1,int i2,int j2) {
    	int[][] clone = cloneArray(board);
    	changePositions(clone, i1, j1, i2, j2);
    	return clone;
    }
    public Iterable<Board> neighbors(){
    	Queue<Board> neighbors = new Queue<>();
    	int[] blankPosition = findBlankPosition();
    	int blankI = blankPosition[0],blankJ = blankPosition[1];
    	enqueuePossibleNeighbors(neighbors, blankI, blankJ);
    	return neighbors;
    }
	private void enqueuePossibleNeighbors(Queue<Board> neighbors, int blankI,
			int blankJ) {
		if (blankI>0){
    		int[][] neighorBoard = cloneAndRotate(board, blankI, blankJ, blankI-1,blankJ);
    		neighbors.enqueue(new Board(neighorBoard));
    	}
    	if (blankI<dimension-1){
    		int[][] neighorBoard = cloneAndRotate(board, blankI, blankJ, blankI+1,blankJ);
    		neighbors.enqueue(new Board(neighorBoard));   		
    	}
    	if (blankJ>0){
    		int[][] neighorBoard = cloneAndRotate(board, blankI, blankJ, blankI,blankJ-1);
    		neighbors.enqueue(new Board(neighorBoard));   		
    	}
    	if (blankJ<dimension-1){
    		int[][] neighorBoard = cloneAndRotate(board, blankI, blankJ, blankI,blankJ+1);
    		neighbors.enqueue(new Board(neighorBoard));   		
    	}
	}
	private int[] findBlankPosition() {
		int[] blankPosition = new int[2];
    	for (int i=0;i<dimension;i++){
    		for (int j=0;j<dimension;j++){
    			int value = board[i][j];
    			if (value==0){
    				blankPosition[0] = i;
    				blankPosition[1] = j;
    			}
    		}
    	}
    	return blankPosition;
	}    
    public String toString(){
    	StringBuilder output = new StringBuilder();
    	output.append(Integer.toString(dimension));
    	for (int i=0;i<dimension;i++){
    		output.append("\n");
    		for (int j=0;j<dimension;j++)
    			output.append(String.format("%2d ", board[i][j]));
    	}
    	return output.toString();
    }
}