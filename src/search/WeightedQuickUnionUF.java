package search;

import java.util.Arrays;


public class WeightedQuickUnionUF extends AbstractUF {

	protected int[] sz;
	
	public WeightedQuickUnionUF(int size) {
		super(size);
		sz = new int[size];
		for (int i = 0; i < size; i++)
			sz[i] = 1;
	}

	private int root(int i) {
		while (i != id[i])
			i = id[i];
		return i;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	@Override
	public void union(int p, int q) {
		System.out.print(String.format("Performing union(%d,%d)\n", p, q));
		int i = root(p);
		int j = root(q);
		if (i == j)
			return;
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}

		System.out.println(Arrays.toString(this.id));
	}
	
	public static void main(String [] args)
	{
		String input = "6-1 4-0 9-6 3-8 7-4 8-0 6-2 1-5 1-0";
		WeightedQuickUnionUF instance = new WeightedQuickUnionUF(10);
		instance.unionsFromString(input);
	}
}
