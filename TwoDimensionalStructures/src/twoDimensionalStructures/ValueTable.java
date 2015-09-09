package twoDimensionalStructures;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ValueTable implements Iterator<HashMap<Column, Object>> {
	
	ColumnsList columns;
	List<HashMap<Column, Object>> rows;
		
	public ValueTable() {
		super();
		this.columns = new ColumnsList();
		this.rows    = new ArrayList<>();
	}
	
	@Override
	public boolean hasNext() {
		return rows.iterator().hasNext();
	}
	@Override
	public HashMap<Column, Object> next() {
		return rows.iterator().next();
	}
	@Override
	public void remove() {
		rows.iterator().remove();
	}

	public void addRow() {
		rows.add(null);
	}
	public void deleteRow(Integer index) {
		rows.remove(index);
	}
	public void removeRow(Integer index) {
		rows.remove(index);
	}

}
