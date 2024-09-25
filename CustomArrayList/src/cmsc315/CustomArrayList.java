package cmsc315;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomArrayList<T> implements List<T> {

	T[] arr;
	int arraySize;
	int listSize;
	
	public CustomArrayList() {
		arr = (T[])(new Object[2]);
		arraySize = 2;
		listSize = 0;
	}
	
	@Override
	public int size() {
		return listSize;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return listSize == 0;
	}

	@Override
	public boolean contains(Object o) {
		throw new java.lang.UnsupportedOperationException();
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
		if (arraySize == listSize)
			doubleArraySize();
		
		arr[listSize] = e;
		listSize++;
		return true;
	}
	
	private void doubleArraySize() {
		T[] newArr = (T[])(new Object[arraySize * 2]);
		
		for (int i = 0; i < arraySize; i++)
			newArr[i] = arr[i];
		
		arraySize *= 2;
		arr = newArr;
	}

	@Override
	public boolean remove(Object o) {
		throw new java.lang.UnsupportedOperationException();
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
		listSize=0;
	}

	@Override
	public T get(int index) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void add(int index, T element) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public T remove(int index) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new java.lang.UnsupportedOperationException();
	}

}
