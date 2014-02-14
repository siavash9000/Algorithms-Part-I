import java.util.Random;

public class PercolationStats {
	double[] results;
	int N;
	int T;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T){
		this.N = N;
		this.T = T;
		results = new double[N];
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
		Random random = new Random() ;
		double steps = 0;
		while(!percolation.percolates()){
			steps++;
			int i = 1 + random.nextInt(n);
			int j = 1 + random.nextInt(n);
			percolation.open(i, j);
		}
		System.out.println("Percolation in step: "+steps+". Threshold of "+steps/((double) n));
		return steps/((double) n);
	}
	// test client, described below
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(N, T);
		
	}
}