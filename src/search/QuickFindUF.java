package search;

import java.util.Arrays;

public class QuickFindUF extends AbstractUF {
	
	public QuickFindUF(int size) {
		super(size);
	}

	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	@Override
	public void union(int p, int q) {
		System.out.print(String.format("Performing union(%d,%d)\n",p,q));
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid)
				id[i] = qid;
		System.out.println(Arrays.toString(this.id));
	}  
	
	public static void main(String [] args)
	{
		String input = "9-0 5-1 9-8 4-5 6-5 2-4";
		QuickFindUF instance = new QuickFindUF(10);
		instance.unionsFromString(input);
	}
}
