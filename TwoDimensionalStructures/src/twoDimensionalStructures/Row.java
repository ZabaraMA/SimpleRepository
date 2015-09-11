package twoDimensionalStructures;

import java.util.HashMap;

import twoDimensionalStructures.*;
import twoDimensionalStructures.Exceptions.*;

public class Row {
	
	HashMap<Column, Object> row;
	ValueTable vtable;

	Row(ValueTable valueTable) {
		
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
		return row.get(vtable.columns.getColumn(index));
	}
	
	public Object getValue(String columnName) {
		return row.get(vtable.columns.getColumn(columnName));
	}

}
