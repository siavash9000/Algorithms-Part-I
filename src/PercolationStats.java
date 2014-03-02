
// TODO: Auto-generated Javadoc
/**
 * The Class PercolationStats.
 */
public class PercolationStats {
	
	/** The results. */
	private double[] results;
	
	/** The t. */
	private int T;
	
	// perform T independent computational experiments on an N-by-N grid
	/**
	 * Instantiates a new percolation stats.
	 *
	 * @param n the n
	 * @param t the t
	 */
	public PercolationStats(int n, int t){
		if (n<=0 || t<=0) {
			throw new IllegalArgumentException("Invalid input");
		}
		this.T = t;
		results = new double[t];
		for (int i=0;i<t;i++){
			results[i] = performPercolationExperiment(n);
		}
	}
	// sample mean of percolation threshold
	/**
	 * Mean.
	 *
	 * @return the double
	 */
	public double mean() {
		return StdStats.mean(results);
	}
	// sample standard deviation of percolation threshold
	/**
	 * Stddev.
	 *
	 * @return the double
	 */
	public double stddev() {
		return StdStats.stddev(results);
	}
	// returns lower bound of the 95% confidence interval
	/**
	 * Confidence lo.
	 *
	 * @return the double
	 */
	public double confidenceLo(){
		return mean()-1.96*stddev()/(Math.sqrt(this.T));
	}
	// returns upper bound of the 95% confidence interval
	/**
	 * Confidence hi.
	 *
	 * @return the double
	 */
	public double confidenceHi(){
		return mean()+1.96*stddev()/(Math.sqrt(this.T));
	}

	/**
	 * Perform percolation experiment.
	 *
	 * @param n the n
	 * @return the double
	 */
	private double performPercolationExperiment(final int n) {
		Percolation percolation = new Percolation(n);
		double openingCount = 0;
		while(!percolation.percolates()){
			int i = 1 + StdRandom.uniform(n);
			int j = 1 + StdRandom.uniform(n);
			//if random cell not open-> 
			//move right till next open cell
			do{
				if (j==n) {
					i=i%n+1;
				}
				j=j%n+1;
			}
			while(percolation.isOpen(i, j));
			openingCount++;
			percolation.open(i, j);
		}
	    return openingCount/((double)n*n);
	}
	
	// test client, described below
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats stats = new PercolationStats(N, T);
		System.out.printf("mean                    = %f \n",stats.mean());
		System.out.printf("standard deviation      = %f \n",stats.stddev());
		System.out.printf("95 confidence interval  = %f  %f \n",stats.confidenceLo(),stats.confidenceHi());
	}
}