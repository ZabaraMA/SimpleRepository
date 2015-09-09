package twoDimensionalStructures;

import java.util.HashMap;

public class Row {
	
	HashMap<Column, Object> row;
	ValueTable vtable;

	public Row(ValueTable valueTable) {
		
		this.row = new HashMap<Column, Object>();
		this.vtable = valueTable;
	}
	
	public void setValue(Column col, Object val) {
		
		//if (!this.vtable.columns.contains(col)) {throw Exception};
		
		for (Class<?> valType : col.getTypeList()) {
			
			try 
				{valType.cast(val);
				return;
				}
			catch (ClassCastException e) {}; 
		}
	}
	
	public Object getValue(Column col, Object val) {
		for (Class<?> valType : col.getTypeList()) {
			
			try 
				{return valType.cast(row.get(col));				}
			catch (ClassCastException e) {}; 
		}
		return null;
	}

}
