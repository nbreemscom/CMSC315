package cmsc315;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class Node<T> {
	T	element;
	Node<T> next;
	Node<T> prev;
}

public class CustomLinkedList<T> implements List<T> {

	Node<T> head;
	Node<T> tail;
	int size;
	
	public CustomLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
		
		/*
		 *  Node cursor = head;
		 *  int size = 0;
		 *  while (cursor != null) {
		 *  	cursor = cursor.next;
		 *  	size++;
		 *  }
		 *  return size;
		 */
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private Node<T> getNode(T e) {
		Node<T> cursor = head;
		while (cursor != null) {
			if (cursor.element.equals(e))
				return cursor;
			cursor = cursor.next;
		}
		return null;
	}
	
	@Override
	public boolean contains(Object o) {
		Node<T> temp = getNode((T)(o));
		if (temp == null)
			return false;
		else
			return true;
	}

	@Override
	public Iterator<T> iterator() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean add(T e) {
		Node<T> temp = new Node<T>();
		temp.element = e;
		if (head == null) {
			temp.next = null;
			temp.prev = null;
			head = temp;
			tail = temp;
		} else {
			temp.next = null;
			temp.prev = tail;
			tail.next = temp;
			tail = temp;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Node<T> temp = getNode((T)(o));
		if (temp == null) return false;
		
		if (temp.prev == null && temp.next == null) {
			head = null;
			tail = null;
			size--;
		} else if (temp.prev != null && temp.next == null) {
			temp.prev.next = null;
			tail = temp.prev;
			size--;
		} else if (temp.prev == null && temp.next != null) {
			temp.next.prev = null;
			head = temp.next;
			size--;
		} else {
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			size--;
		}
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void clear() {
		size = 0;
		head = null;
		tail = null;
	}

	@Override
	public T get(int index) {
		Node<T> cursor = head;
		for (int i = 0; i < index; i++) {
			if (cursor == null) {
				throw new java.lang.IndexOutOfBoundsException();
			}
			cursor = cursor.next;
		}
		return cursor.element;
	}

	@Override
	public T set(int index, T element) {
		Node<T> cursor = head;
		for (int i = 0; i < index; i++) {
			if (cursor == null) 
				throw new java.lang.IndexOutOfBoundsException();
			cursor = cursor.next;
		}
		T temp = cursor.element;
		cursor.element = element;
		return temp;
	}

	@Override
	public void add(int index, T element) {
		if (size == 0) {
			if (index > 0)
				throw new java.lang.IndexOutOfBoundsException();
			add(element);
			return;
		}
		
		if (index == size) { add(element); return; }
		
		Node<T> cursor = head;
		for (int i = 0; i < index; i++) {
			if (cursor == null)
				throw new java.lang.IndexOutOfBoundsException();
			cursor = cursor.next;
		}
		Node<T> temp = new Node<T>();
		temp.element = element;
		temp.next = cursor;
		temp.prev = cursor.prev;
		if (cursor.prev != null) {
			cursor.prev.next = temp;
		} else {
			head = temp;
		}
		cursor.prev = temp;
		size++;
	}

	@Override
	public T remove(int index) {
		Node<T> cursor = head;
		for (int i = 0; i < index; i++) {
			if (cursor == null) throw new java.lang.IndexOutOfBoundsException();
			cursor = cursor.next;
		}
		
		T temp = cursor.element;
		if (cursor.prev == null && cursor.next == null) {
			clear();
		} else if (cursor.prev == null) {
			// remove first item from list
			cursor.next.prev = null;
			head = cursor.next;
			size--;
		} else if (cursor.next == null) {
			cursor.prev.next = null;
			tail = cursor.prev;
			size--;
		} else {
			// removing an item from the middle of list
			cursor.prev.next = cursor.next;
			cursor.next.prev = cursor.prev;
			size--;
		}
		return temp;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
