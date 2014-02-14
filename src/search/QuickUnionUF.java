package search;

import java.util.Arrays;


public class QuickUnionUF extends AbstractUF {

	public QuickUnionUF(int size) {
		super(size);
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
		System.out.print(String.format("Performing union(%d,%d)\n",p,q));
		int i = root(p);
		int j = root(q);
		id[i] = j;
		System.out.println(Arrays.toString(this.id));
	}
	
	public static void main(String [] args)
	{
		String input = "7-2 7-5 5-9 1-0 5-4 8-6 9-3 8-0 0-3 ";
		QuickUnionUF instance = new QuickUnionUF(10);
		instance.unionsFromString(input);
	}
}
