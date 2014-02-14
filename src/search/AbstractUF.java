package search;

public abstract class AbstractUF {

	protected int[] id;

	public abstract void union(int p, int q);

	public AbstractUF(int size) {
		initArray(size);
	}

	protected void initArray(int size) {
		id = new int[size];
		for (int i = 0; i < size; i++)
			id[i] = i;
	}
	
	public void unionsFromString(String commandString) {
		String[] tuples = commandString.split(" ");
		for (String tuple: tuples){
			String[] unionCommand = tuple.split("-");
			int p = Integer.parseInt(unionCommand[0]);
			int q = Integer.parseInt(unionCommand[1]);
			this.union(p, q);
		}
	}

}