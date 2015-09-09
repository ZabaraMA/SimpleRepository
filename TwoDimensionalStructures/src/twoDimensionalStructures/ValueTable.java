package twoDimensionalStructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ValueTable implements Iterator<HashMap<String, Object>> {
	
	ColumnsList columns;
	List<HashMap<String, Object>> rows;
		
	public ValueTable() {
		super();
		this.columns = new ColumnsList(this);
		this.rows    = new ArrayList<>();
	}
	
	@Override
	public boolean hasNext() {
		return rows.iterator().hasNext();
	}
	@Override
	public HashMap<String, Object> next() {
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
	
	void deleteColumn(String columnName) {
		System.out.print("������� ������");
		
	}
	
	void deleteColumns(Collection<?> c) {
		System.out.print("������� ������");	
	}

}
