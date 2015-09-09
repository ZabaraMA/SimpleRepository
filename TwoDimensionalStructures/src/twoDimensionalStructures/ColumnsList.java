package twoDimensionalStructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class ColumnsList implements Iterable<String>, RandomAccess, Cloneable, java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	List<String> cList;
	HashMap<String, List<Class<?>>> columnsTypes;
	ValueTable vt;
	
		ColumnsList(ValueTable vt) {
		super();
		this.cList        = new ArrayList<String>();
		this.columnsTypes = new HashMap<>();
		this.vt = vt;
	}

	private void checkName(String columnName) throws EmptyColumnNameException {
		
		if (columnName.trim().isEmpty()) 
			{throw new EmptyColumnNameException();};
	}
	
	private void checkTypeList(String columnName, List<Class<?>> typeList) throws EmptyTypeListException, NullTypeColumnException  {
		
		if (typeList.isEmpty()) 
			{throw new EmptyTypeListException(columnName);};
			
		for (Class<?> type : typeList) 
			{if (type == null) {throw new NullTypeColumnException(columnName, typeList.indexOf(type));}};	
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

	public Iterator<String> iterator() {
		return cList.iterator();
	}

	public Object[] toArray() {
		return cList.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return cList.toArray(a);
	}

	public boolean add(String columnName, List<Class<?>> types, int index) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		checkName(columnName);
		checkTypeList(columnName, types);
		if (!cList.contains(columnName)) {
			this.columnsTypes.put(columnName, types);
			if (index == -1) 
				return cList.add(columnName);
			else {
				cList.add(index, columnName);
				return true;
			}
		}
		else throw new DuplicateNameColumnException(columnName); 
	}
	
	public boolean add(String columnName, List<Class<?>> types) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		return add(columnName, types, -1);
	}

	public boolean remove(Object o) {
		this.columnsTypes.remove(o);
		this.vt.deleteColumn((String) o);
		return cList.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return cList.containsAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		for (Object col : c) {
			this.columnsTypes.remove(col);
		}
		
		this.vt.deleteColumns(c);
		return cList.removeAll(c);
	}

		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cList == null) ? 0 : cList.hashCode());
		result = prime * result
				+ ((columnsTypes == null) ? 0 : columnsTypes.hashCode());
		result = prime * result + ((vt == null) ? 0 : vt.hashCode());
		return result;
	}

	
	public String getName(int index) {
		return cList.get(index);
	}

	public boolean remove(int index) {
		return cList.remove(cList.get(index));
	}

	public int indexOf(Object o) {
		return cList.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return cList.lastIndexOf(o);
	}

	public ListIterator<String> listIterator() {
		return cList.listIterator();
	}

	public ListIterator<String> listIterator(int index) {
		return cList.listIterator(index);
	}

	public List<String> subListOfColumnNames(int fromIndex, int toIndex) {
		return cList.subList(fromIndex, toIndex);
	}
	
}

class EmptyColumnNameException extends Exception {

	private static final long serialVersionUID = 1L;
	public EmptyColumnNameException() {super("Column name is empty!");} 		
	
}

class EmptyTypeListException extends Exception {

	private static final long serialVersionUID = 1L;
	public EmptyTypeListException(String columnName) {super("Type list of column '" + columnName + "' is empty!");} 		
	
}

class NullTypeColumnException extends Exception {

	private static final long serialVersionUID = 1L;
	public NullTypeColumnException(String columnName, int i) {super("Type list of column '" + columnName + "' contains null type (index = '" + i + "') !");} 		
	
}

class DuplicateNameColumnException extends Exception {

	private static final long serialVersionUID = 1L;
	public DuplicateNameColumnException(String columnName) {super("Column with name '" + columnName + "' already exists!");} 		
	
}
