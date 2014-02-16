
// TODO: Auto-generated Javadoc
/**
 * The Class Percolation.
 */
public class Percolation {
	   
   	/** The grid. */
   	private boolean[][] grid;
	   
   	/** The grid size. */
   	private int gridSize;
	   
   	/** The unionfind. */
   	private WeightedQuickUnionUF unionfind;
	   // create N-by-N grid, with all sites blocked
	   // false = closed, true = open
	   /**
   	 * Instantiates a new percolation.
   	 *
   	 * @param n the n
   	 */
   	public Percolation(int n){
		   gridSize = n;
		   unionfind = new WeightedQuickUnionUF(n*n+2);
		   grid = new boolean[n][n];
		   //N+1 = Upper parent Node, N+2=lower parent node
		   for (int i=0;i<gridSize;i++)
			   for (int j=0;j<gridSize;j++)
					grid[i][j] = false;
	   }
       // open site (row i, column j) if it is not already
	   /**
        * Open.
        *
        * @param i the i
        * @param j the j
        */
       public void open(final int i,final int j) { 
		   assertValidIndices(i,j);
		   if (isOpen(i, j))
			   return;
		   //open site
		   grid[i-1][j-1] = true;
		   //add conncetions to open neighbours
		   int cellIndex = mapGridToUnion(i, j);
		   if (i>1 && isOpen(i-1,j)){
			   int upperCell = mapGridToUnion(i-1, j);
			   unionfind.union(cellIndex, upperCell);
		   }
		   if (i<this.gridSize && isOpen(i+1,j)){
			   int lowerCell = mapGridToUnion(i+1, j);
			   unionfind.union(cellIndex, lowerCell);
		   }
		   if (j>1 && isOpen(i,j-1)){
				int leftCell = mapGridToUnion(i, j-1);
				unionfind.union(cellIndex, leftCell);
		   }
		   if (j<this.gridSize && isOpen(i,j+1)){
			   int rightCell = mapGridToUnion(i, j+1);
			   unionfind.union(cellIndex, rightCell);
		   }
		   if (i == 1)
			   unionfind.union(cellIndex,gridSize*gridSize);
           if (i == gridSize && isFull(i, j) )
        	   unionfind.union(cellIndex,gridSize*gridSize+1);
           if (isFull(i, j))
        	   checkForPercolation(i,j);
	   }
	   
	   /**
   	 * Check for percolation.
	 * @param j2 
	 * @param i 
   	 */
   	private void checkForPercolation(int i, int j) {
		   for(int col=1;col<=gridSize;col++){
			   if(unionfind.connected(mapGridToUnion(gridSize, col),mapGridToUnion(i, j)))
				   unionfind.union(mapGridToUnion(gridSize, col),gridSize*gridSize+1);
		   }
	   }
	// is site (row i, column j) open?
	   /**
	 * Checks if is open.
	 *
	 * @param i the i
	 * @param j the j
	 * @return true, if is open
	 */
	public boolean isOpen(int i, int j) {
		   assertValidIndices(i,j);
		   return grid[i-1][j-1];
	   }
	   
	   /**
   	 * Assert valid indices.
   	 *
   	 * @param i the i
   	 * @param j the j
   	 */
   	private void assertValidIndices(int i,int j){
		   if (i<=0 || i > gridSize || j <= 0 || j > gridSize)
			   throw new IndexOutOfBoundsException(
					   String.format("Invalid Indices (%d,%d)",i,j));
	   }
	   
	   /**
   	 * Map grid to union.
   	 *
   	 * @param i the i
   	 * @param j the j
   	 * @return the int
   	 */
   	private int mapGridToUnion(int i, int j){
		   assertValidIndices(i, j);
		   return (i-1)*this.gridSize + (j-1);
		   
	   }
	   // is site (row i, column j) full?
	   /**
   	 * Checks if is full.
   	 *
   	 * @param i the i
   	 * @param j the j
   	 * @return true, if is full
   	 */
   	public boolean isFull(int i, int j){
		   if (isOpen(i, j) 
				   && 
				   unionfind.connected(gridSize*gridSize, mapGridToUnion(i, j)))
			   return true;
		   else 
			   return false;
	   }
	   // does the system percolate?
	   /**
   	 * Percolates.
   	 *
   	 * @return true, if successful
   	 */
   	public boolean percolates(){
		   return unionfind.connected(gridSize*gridSize, gridSize*gridSize+1);
	   }		
}
