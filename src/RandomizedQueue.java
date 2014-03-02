import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int size;

	// construct an empty randomized queue
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		items = (Item[]) new Object[2];
		size = 0;
	}

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		assert capacity >= size;
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = items[i];
		}
		items = temp;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	// delete and return a random item
	public Item dequeue() {
		if (size == 0)
			throw new NoSuchElementException("");
		int position = StdRandom.uniform(size);
		Item result = items[position];
		items[position] = items[size - 1];
		items[size - 1] = null;
		size--;
		return result;
	}

	private int getFreeCapacity() {
		return items.length - size;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException("attempted to enqueue null");
		if (getFreeCapacity() == 0)
			resize(size * 2);
		size++;
		items[size - 1] = item;
	}

	// return (but do not delete) a random item
	public Item sample() {
		return items[StdRandom.uniform(size)];
	}

	private class RandomIterator implements Iterator<Item> {
		private RandomizedQueue<Item> clone;

		public RandomIterator(RandomizedQueue<Item> queue) {
			clone = new RandomizedQueue<>();
			for (int i=0;i<size;i++)
				clone.enqueue(items[i]);
			}

		@Override
		public boolean hasNext() {
			return !clone.isEmpty();
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return clone.dequeue();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomIterator(this);
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> queue = new RandomizedQueue<>();
		for (int i = 0; i < 10; i++)
			queue.enqueue(i);
		Iterator<Integer> iterator = queue.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
		System.out.println("--------------------------------------------------------------");
		while (!queue.isEmpty())
			System.out.println(queue.dequeue());
	}
}
