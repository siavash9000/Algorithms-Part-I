import java.util.Random;

public class PercolationStats {
	double[] results;
	int N;
	int T;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T){
		this.N = N;
		this.T = T;
		results = new double[T];
		for (int i=0;i<T;i++)
			results[i] = performPercolationExperiment(N);
	}
	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(results);
	}
	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(results);
	}
	// returns lower bound of the 95% confidence interval
	public double confidenceLo(){
		return mean()-1.96*stddev()/(Math.sqrt(this.T));
	}
	// returns upper bound of the 95% confidence interval
	public double confidenceHi(){
		return mean()+1.96*stddev()/(Math.sqrt(this.T));
	}

	private double performPercolationExperiment(int n) {
		Percolation percolation = new Percolation(n);
		Random random = new Random(System.currentTimeMillis()) ;
		double openingCount = 0;
		while(!percolation.percolates()){
			int i = 1 + random.nextInt(n);
			int j = 1 + random.nextInt(n);
			if (!percolation.isOpen(i, j)){
				openingCount++;
				percolation.open(i, j);
			}
		}
	    return openingCount/((double)n*n);
	}
	
	// test client, described below
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		if (N<=0 || T<=0)
			throw new IllegalArgumentException("Invalid input");
		PercolationStats stats = new PercolationStats(N, T);
		//System.out.println("N="+N+",T="+T);
		//System.out.println(Arrays.toString(stats.results));
		System.out.printf("mean                    = %f \n",stats.mean());
		System.out.printf("standard deviation      = %f \n",stats.stddev());
		System.out.printf("95 confidence interval  = %f  %f \n",stats.confidenceLo(),stats.confidenceHi());
	}
}