public class Percolation {
	   private boolean[][] grid;
	   private int grid_size;
	   private WeightedQuickUnionUF unionfind;
	   // create N-by-N grid, with all sites blocked
	   // 0 = closed, 1 = open
	   public Percolation(int N){
		   grid_size = N;
		   unionfind = new WeightedQuickUnionUF(N*N+2);
		   grid = new boolean[N][N];
		   //N+1 = Upper parent Node, N+2=lower parent node
		   for (int i=0;i<grid_size;i++)
			   for (int j=0;j<grid_size;j++)
					grid[i][j] = false;
	   }
       // open site (row i, column j) if it is not already
	   public void open(int i, int j) { 
		   assertValidIndices(i,j);
		   if (grid[i-1][j-1])
			   return;
		   //open site
		   grid[i-1][j-1] = true;
		   //add conncetions to open neighbours
		   int cell_index = mapGridToUnion(i, j);
		   if (i>1 && isOpen(i-1,j)){
			   int upper_cell = mapGridToUnion(i-1, j);
			   unionfind.union(cell_index, upper_cell);
		   }
		   if (i<this.grid_size && isOpen(i+1,j)){
			   int lower_cell = mapGridToUnion(i+1, j);
			   unionfind.union(cell_index, lower_cell);
		   }
		   if (j>1 && isOpen(i,j-1)){
				int left_cell = mapGridToUnion(i, j-1);
				unionfind.union(cell_index, left_cell);
		   }
		   if (j<this.grid_size && isOpen(i,j+1)){
			   int right_cell = mapGridToUnion(i, j+1);
			   unionfind.union(cell_index, right_cell);
		   }
		   if (i == 1)
			   unionfind.union(cell_index,grid_size*grid_size);
           if (i == grid_size && isFull(i, j) )
        	   unionfind.union(cell_index,grid_size*grid_size+1);
	   }
	   
	   // is site (row i, column j) open?
	   public boolean isOpen(int i, int j) {
		   assertValidIndices(i,j);
		   return grid[i-1][j-1];
	   }
	   
	   private void assertValidIndices(int i,int j){
		   if (i<=0 || i > grid_size || j <= 0 || j > grid_size)
			   throw new IndexOutOfBoundsException(
					   String.format("Invalid Indices (%d,%d)",i,j));
	   }
	   
	   private int mapGridToUnion(int i, int j){
		   assertValidIndices(i, j);
		   if (i>1)
			   return (i-1)*this.grid_size + (j-1);
		   else
			   return j-1;
	   }
	   // is site (row i, column j) full?
	   public boolean isFull(int i, int j){
		   if (isOpen(i, j) && 
				   unionfind.connected(grid_size*grid_size, mapGridToUnion(i, j)))
			   return true;
		   else 
			   return false;
	   }
	   // does the system percolate?
	   public boolean percolates(){
		   return unionfind.connected(grid_size*grid_size, grid_size*grid_size+1);
	   }		
}
