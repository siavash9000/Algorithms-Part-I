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
    public Board twin(){
    	int[][] twin = cloneArray(board);
    	for(int i=0;i<dimension;i++){
    		if (twin[i][0]!=0 && twin[i][1]!=0){
    			int temp = twin[i][0];
    			twin[i][0] = twin[i][1];
    			twin[i][1] = temp;
    			break;
    		}	
    	}
    	return new Board(twin);
    }
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
    /*public Iterable<Board> neighbors()     // all neighboring boards
    public String toString()               // string representation of the board (in the output format specified below)
	*/
}