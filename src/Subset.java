public class Subset {

	public static void main(String[] args) {
		Integer k = Integer.parseInt(args[0]);
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			queue.enqueue(item);
		}
		for (int i=0;i<k;i++)
			System.out.println(queue.dequeue());
		
	}

}
