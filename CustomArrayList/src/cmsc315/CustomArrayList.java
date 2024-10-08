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
		return (indexOf(o) >= 0);
	}

	@Override
	public Iterator<T> iterator() {
		return new CustomArrayListIterator();
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
		T temp = (T)(o);
		
		for (int i = 0; i < listSize; i++) {
			if (arr[i].equals(temp)) {
				for (int j = i; j < listSize-1; j++)
					arr[j] = arr[j+1];
				listSize--;
				return true;
			}
		}
		return false;
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
		if (index < 0 || index >= listSize)
			throw new java.lang.IndexOutOfBoundsException();
		
		return arr[index];
	}

	@Override
	public T set(int index, T element) {
		if (index < 0 || index >= listSize)
			throw new java.lang.IndexOutOfBoundsException();
		
		T temp = arr[index];
		arr[index]= element;
		return temp;
	}

	@Override
	public void add(int index, T element) {
		if (arraySize == listSize)
			doubleArraySize();
		
		for (int i = listSize-1; i >= index; i--)
			arr[i+1] = arr[i];
		
		arr[index] = element;
		listSize++;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= listSize)
			throw new java.lang.IndexOutOfBoundsException();
		
		T temp = arr[index];
		for (int j = index; j < listSize-1; j++)
			arr[j] = arr[j+1];
		listSize--;
		return temp;
	}

	@Override
	public int indexOf(Object o) {
		T temp = (T)(o);
		for (int i = 0; i < listSize; i++) {
			if (arr[i].equals(temp))
				return i;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		T temp = (T)(o);
		for (int i = listSize-1; i >= 0; i--) {
			if (arr[i].equals(temp))
				return i;
		}
		return -1;

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

	class CustomArrayListIterator<T> implements Iterator<T> {
		int index;
		
		public CustomArrayListIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < listSize;
		}

		@Override
		public T next() {
			T temp = (T)(arr[index]);
			index++;
			return temp;
		}
		
		
	}
}
