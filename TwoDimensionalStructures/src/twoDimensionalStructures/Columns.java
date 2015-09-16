package twoDimensionalStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;

public class Columns implements Iterable<Column>, RandomAccess, java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	List<Column> cList;
	HashMap<String, Column> cNames;
	ValueTable vt;
	
	protected Columns(ValueTable vt) {
		super();
		this.cList  = new ArrayList<Column>();
		this.cNames = new HashMap<String, Column>();
		this.vt     = vt;
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
		return new ColumnsListIterator();
	}

	public Object[] toArray() {
		return cList.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return cList.toArray(a);
	}

    public void add(Column col, int index) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		
    	cList.add(index, col);
		cNames.put(col.getColumnName(), col);
	}
    
    public boolean add(Column col) {
		
    	boolean ind = cList.add(col);
    	cNames.put(col.getColumnName(), col);
    	return ind;
	}
	
	public void add(String columnName, List<Class<?>> types, int index) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		
		Column newColumn = new Column(this, columnName, types);
		add(newColumn, index);
	}
	
	public boolean add(String columnName, List<Class<?>> types) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		
		Column newColumn = new Column(this, columnName, types);
		return add(newColumn);
	}
	
	public boolean add(String columnName, Class<?> columnClass) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		return add(columnName, new ArrayList<Class<?>>(Arrays.asList(columnClass)));
	}
	
	public boolean add(String columnName, String typeName) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException, ClassNotFoundException {
		return add(columnName, Class.forName(typeName));
	}

	public boolean remove(Column o) {
		this.vt.deleteColumn(o);
		this.cNames.remove(o.getColumnName());
		o.colList    = null;
		o.columnName = null;
		o.typeList   = null;
		return cList.remove(o);
	}
	
	public boolean remove(int index) {
		Column col = cList.get(index);
		cNames.remove(col.getColumnName());
		return remove(col);
	}
	
	public boolean containsAll(Collection<? extends Column> c) {
		return cList.containsAll(c);
	}

	public boolean removeAll(Collection<? extends Column> c) {
		this.vt.deleteColumns(c);
		
		for(Column col : c) {cNames.remove(col.getColumnName());};
		return cList.removeAll(c);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cList == null) ? 0 : cList.hashCode());
		result = prime * result
				+ ((cNames == null) ? 0 : cNames.hashCode());
		result = prime * result + ((vt == null) ? 0 : vt.hashCode());
		return result;
	}
	
	public Column getColumn(int index) {
		return cList.get(index);
	}
	
	public Column getColumn(String columnName) {
		return cNames.get(columnName);
	}

	public int indexOf(Object o) {
		return cList.indexOf(o);
	}

	public ListIterator<Column> listIterator() {
		return new ColumnsListIterator();
	}

	public ListIterator<Column> listIterator(int index) {
		return new ColumnsListIterator(index);
	}

	public List<Column> subListOfColumnNames(int fromIndex, int toIndex) {
		return cList.subList(fromIndex, toIndex);
	}
	
	@Override
	public String toString() {
		return cList.toString();
	}
	
		
	private class ColumnsListIterator implements ListIterator<Column> {

		ListIterator<Column> li;
		
		public ColumnsListIterator() {
			li = cList.listIterator();
		}
		
		public ColumnsListIterator(int index) {
			li = cList.listIterator(index);
		}

		@Override
		public boolean hasNext() {
			return li.hasNext();
		}

		@Override
		public Column next() {
			return li.next();
		}

		@Override
		public boolean hasPrevious() {
			return li.hasPrevious();
		}

		@Override
		public Column previous() {
			return li.previous();
		}

		@Override
		public int nextIndex() {
			return li.nextIndex();
		}

		@Override
		public int previousIndex() {
			return li.previousIndex();
		}

		@Override
		public void remove() {
			Columns.this.remove(li.next());
		}

		@Override
		public void set(Column e) {
			li.set(e);	
		}

		@Override
		public void add(Column e) {
			li.add(e);			
		}
	}


}
