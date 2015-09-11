package twoDimensionalStructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import twoDimensionalStructures.*;
import twoDimensionalStructures.Exceptions.*;

public class Row {
	
	HashMap<Column, Object> row;
	ValueTable vtable;

	protected Row(ValueTable valueTable) {
		
		this.row = new HashMap<Column, Object>();
		this.vtable = valueTable;
	}
	
	public void setValue(Column col, Object val) throws NoSuchColumnInValueTable {

		if (!this.vtable.columns.contains(col)) 
			{throw new NoSuchColumnInValueTable(col.getColumnName());};

		for (Class<?> valType : col.getArrayOfTypes()) {

			try	{
				valType.cast(val);
				row.put(col, val);
				return;
				}
			catch (ClassCastException e) {}; 
		}
	}
	
	public void setValue(int index, Object val) throws NoSuchColumnInValueTable {
		setValue(vtable.columns.getColumn(index), val);		
	}
	
	public void setValue(String columnName, Object val) throws NoSuchColumnInValueTable {
		setValue(vtable.columns.getColumn(columnName), val);		
	}
	
	public Object getValue(Column col) {
		return row.get(col);
	}
	
	public Object getValue(int index) {
		return getValue(vtable.columns.getColumn(index));
	}
	
	public Object getValue(String columnName) {
		return getValue(vtable.columns.getColumn(columnName));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		result = prime * result + ((vtable == null) ? 0 : vtable.hashCode());
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
		Row other = (Row) obj;
		
		if (!vtable.equals(other.vtable))
			return false;
		for(Column col : vtable.getColumns()) {
			if (!this.getValue(col).equals(other.getValue(col))) {
				return false;
			}
		};		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		for (Column col : vtable.getColumns()) {
			sb.append(",[[");
			sb.append(col.getColumnName());
			sb.append("],[");
			sb.append(row.get(col));
			sb.append("]]");
		}
		return sb.toString();
	}
}
