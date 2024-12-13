import java.util.Iterator;
import java.util.LinkedList;

public class HashSet<T> implements java.lang.Iterable<T> {
	private LinkedList<T>[] ht;
	private float LOAD_FACTOR = .75f;
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashSet() {
		ht = new LinkedList[4];
		
		for (int i=0; i < 4; i++)
			ht[i] = new LinkedList<T>();
		size = 0;
	}
	
	private void resize() {
		@SuppressWarnings("unchecked")
		int new_size = ht.length << 1;
		LinkedList<T>[] new_ht = new LinkedList[new_size];
		for (int i = 0; i < new_size; i++) {
			new_ht[i] = new LinkedList<T>();
		}
		
		for (int i = 0; i < ht.length; i++) {
			for (T item : ht[i]) {
				int index = item.hashCode() % new_size;
				new_ht[index].add(item);
			}
		}
		
		ht = new_ht;
	}
	
	public boolean add(T x) {
		
		int index = x.hashCode() % ht.length;
		if (ht[index].contains(x)) {
			return false;
		} else {
			ht[index].add(x);
			size++;
			if (size / ht.length > LOAD_FACTOR)
				resize();
			return true;
		}
	}
	
	public boolean contains(T x) {
		return ht[x.hashCode() % ht.length].contains(x);
	}
	
	public boolean remove(T x) {
		boolean was_there = (ht[x.hashCode() % ht.length]).remove(x);
		if (was_there) size--;
		return was_there;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator<T>();
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < ht.length; i++) {
			s = s + ht[i].toString() + ", ";
		}
		return s;
	}
	
	class HashSetIterator<T> implements Iterator{

		int index;
		Iterator<T> iteratorCursor;
		
		public HashSetIterator() {
			index = 0;
			iteratorCursor = (Iterator<T>) ht[index].iterator();
			while (!iteratorCursor.hasNext()) {
				index++;
				if (index >= ht.length)
					return;
				iteratorCursor = (Iterator<T>) ht[index].iterator();
			}
		}
		
		@Override
		public boolean hasNext() {
			return iteratorCursor.hasNext();
		}

		@Override
		public Object next() {
			T temp = iteratorCursor.next();
			while (!iteratorCursor.hasNext()) {
				index++;
				if (index >= ht.length)
					break;
				iteratorCursor = (Iterator<T>) ht[index].iterator();
			}
			return temp;
		}
		
	}
}
