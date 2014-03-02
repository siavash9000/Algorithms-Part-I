import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first, last;
	private int size;

	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		size = 0;
	}

	private class Node {
		Item item;
		Node next;
		Node previous;
	}

	public int size() {
		return size;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// insert the item at the front
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node newfirst = new Node();
		newfirst.item = item;
		newfirst.previous = null;
		if (isEmpty())
			last = newfirst;
		else {
			first.previous = newfirst;
			newfirst.next = first;
		}
		first = newfirst;
		size++;
	}

	// insert the item at the end
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node newlast = new Node();
		newlast.item = item;
		newlast.next = null;
		if (isEmpty())
			first = newlast;
		else {
			newlast.previous = last;
			last.next = newlast;
		}
		last = newlast;
		size++;
	}

	// delete and return the item at the front
	public Item removeFirst() {
		if (size == 0)
			throw new NoSuchElementException();
		Node currentFirst = first;
		first = first.next;
		first.previous = null;
		size--;
		return currentFirst.item;
	}

	// delete and return the item at the end public
	public Item removeLast() {
		if (size == 0)
			throw new NoSuchElementException();
		Node currentLast = last;
		last = last.previous;
		last.next = null;
		size--;
		return currentLast.item;
	}

	private class DequeIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	// return an iterator over items in order from front to end
	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	public static void main(String[] args) {
		String[] input = "to be or not to - be - - that - - - is".split(" ");
		Deque<String> deque = new Deque<String>();
		for (String s: input) {
			if (s.equals("-"))
				System.out.print(deque.removeLast()+" ");
			else
				deque.addLast(s);
		}
	}
}