package twoDimensionalStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;

public class Column {
	
	String columnName;
	List<Class<?>> typeList;
	Columns colList; 
	
	protected Column(Columns colList, String columnName, List<Class<?>> typeList) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		super();
		this.colList = colList;
		this.setColumnName(columnName);
		this.setTypeList(typeList);
	}
	
	protected Column(Columns colList, String columnName, Class<?> type) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		super();
		this.colList = colList;
		this.setColumnName(columnName);
		this.setTypeList(new ArrayList<Class<?>>(Arrays.asList(type)));
	}
	
	protected Column(Columns colList, String columnName, String className) throws ClassNotFoundException, EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		
		this(colList, columnName, Class.forName(className));
	}
		
	
	private void checkName(String columnName) throws EmptyColumnNameException, DuplicateNameColumnException {
		
		if (columnName.trim().isEmpty()) 
			{throw new EmptyColumnNameException();};
			
		Column col = colList.getColumn(columnName);
		if (col != null && col.getColumnName().equals(columnName) && col != this) 
			{throw new DuplicateNameColumnException(columnName);};
		
	}
	
	private void checkTypeList(List<Class<?>> typeList) throws EmptyTypeListException, NullTypeColumnException  {
		
		if (typeList.isEmpty()) 
			{throw new EmptyTypeListException(columnName);};
			
		for (Class<?> type : typeList) 
			{if (type == null) {throw new NullTypeColumnException(columnName, typeList.indexOf(type));}};	
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) throws EmptyColumnNameException, DuplicateNameColumnException {
		checkName(columnName);
		this.columnName = columnName.trim();
	}

	
	private void setTypeList(List<Class<?>> typeList) throws EmptyTypeListException, NullTypeColumnException {
		checkTypeList(typeList);
		this.typeList = typeList;
	}
	
	
	Class<?>[] getArrayOfTypes() { 
		return (Class[]) typeList.toArray(new Class[]{});
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result
				+ ((typeList == null) ? 0 : typeList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Column other = (Column) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (typeList == null) {
			if (other.typeList != null)
				return false;
		} else if (typeList.size() != other.typeList.size())
			return false;
		else if (!typeList.containsAll(other.typeList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Name = " + columnName + " [typeList=" + typeList + "]";
	}
}	