package twoDimensionalStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Column {
	
	private String columnName;
	private List<Class<?>> typeList = new ArrayList<>();
	
	public Column(String columnName, List<Class<?>> typeList) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException {
		super();
		this.setColumnName(columnName);
		this.setTypeList(typeList);
	}
	
	public Column(String columnName, Class<?> type) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException {
		super();
		this.setColumnName(columnName);
		this.setTypeList(new ArrayList<Class<?>>(Arrays.asList(type)));
	}
	
	public Column(String columnName, String className) throws ClassNotFoundException, EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException {
		
		this(columnName, Class.forName(className));
	}
		
	
	private void checkName(String columnName) throws EmptyColumnNameException {
		
		if (columnName.trim().isEmpty()) 
			{throw new EmptyColumnNameException();};
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

	public void setColumnName(String columnName) throws EmptyColumnNameException {
		checkName(columnName);
		this.columnName = columnName.trim();
	}

	
	private void setTypeList(List<Class<?>> typeList) throws EmptyTypeListException, NullTypeColumnException {
		checkTypeList(typeList);
		this.typeList = typeList;
	}
	
	
	List<Class<?>> getTypeList() { 
		return typeList;
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
