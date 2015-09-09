package twoDimensionalStructures;

import java.util.HashMap;

public class Row {
	
	HashMap<String, Object> row;
	ValueTable vtable;

	public Row(ValueTable valueTable) {
		
		this.row = new HashMap<String, Object>();
		this.vtable = valueTable;
	}
	
	public void setValue(String col, Object val) {
		
		//if (!this.vtable.columns.contains(col)) {throw Exception};
		
//		for (Class<?> valType : col.getTypeList()) {
//			
//			try 
//				{valType.cast(val);
//				return;
//				}
//			catch (ClassCastException e) {}; 
//		}
	}
	
	public Object getValue(String col, Object val) {
//		for (Class<?> valType : col.getTypeList()) {
//			
//			try 
//				{return valType.cast(row.get(col));				}
//			catch (ClassCastException e) {}; 
//		}
		return null;
	}

}
