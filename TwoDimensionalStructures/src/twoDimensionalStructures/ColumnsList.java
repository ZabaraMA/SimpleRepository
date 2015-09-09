package twoDimensionalStructures;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class ColumnsList extends AbstractList<Column>
	implements List<Column>, RandomAccess, Cloneable, java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	List<Column> cList = new ArrayList<Column>();
	
	private boolean isDuplicateName(Column col) {
		for (Column exCol : cList) {
			if (exCol.getColumnName().equals(col.getColumnName())) {
				//ignore duplicate column name
				return true;
			}
		}
		
		return false;
	}
	
	private Collection<? extends Column> deleteDuplicateColumns(Collection<? extends Column> c) {
		LinkedList<Column> newC = new LinkedList<Column>();
		for (Column col : c) {
			if (!isDuplicateName(col)) {
				newC.add(col);
			}
		}
		return newC;
	}
	
	public int size() {
		return cList.size();
	}

	public boolean isEmpty() {
		return cList.isEmpty();
	}

	public boolean contains(Object o) {
		return cList.contains(o);
	}

	public Iterator<Column> iterator() {
		return cList.iterator();
	}

	public Object[] toArray() {
		return cList.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return cList.toArray(a);
	}

	public boolean add(Column e) {
		if (!isDuplicateName(e)) return cList.add(e);
		else return false; 
	}

	public boolean remove(Object o) {
		return cList.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return cList.containsAll(c);
	}

	public boolean addAll(Collection<? extends Column> c) {
		return cList.addAll(deleteDuplicateColumns(c));
	}

	public boolean addAll(int index, Collection<? extends Column> c) {
		return cList.addAll(index, deleteDuplicateColumns(c));
	}

	public boolean removeAll(Collection<?> c) {
		return cList.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return cList.retainAll(c);
	}

	public void clear() {
		cList.clear();
	}

	public boolean equals(Object o) {
		return cList.equals(o);
	}

	public int hashCode() {
		return cList.hashCode();
	}

	public Column get(int index) {
		return cList.get(index);
	}

	public Column set(int index, Column element) {
		return cList.set(index, element);
	}

	public void add(int index, Column element) {
		if (!isDuplicateName(element)) cList.add(index, element);
	}

	public Column remove(int index) {
		return cList.remove(index);
	}

	public int indexOf(Object o) {
		return cList.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return cList.lastIndexOf(o);
	}

	public ListIterator<Column> listIterator() {
		return cList.listIterator();
	}

	public ListIterator<Column> listIterator(int index) {
		return cList.listIterator(index);
	}

	public List<Column> subList(int fromIndex, int toIndex) {
		return cList.subList(fromIndex, toIndex);
	}
	
}
