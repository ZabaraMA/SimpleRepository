package twoDimensionalStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NoSuchColumnInValueTable;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;


public class ValueTable implements Cloneable {
	
	Columns columns;
	Rows rows;
		
	public ValueTable() {
		super();
		this.columns = new Columns(this);
		this.rows    = new Rows(this);
	}
	
	public Columns getColumns() {
		return columns;
	}
	
	public Rows getRows() {
		return rows;
	}
		
	public void deleteColumn(Column column) {
		
		for (Row row : rows) {
			row.row.remove(column);
		}
	}
	
	public void deleteColumns(Collection<? extends Column> c) {
		for (Column col : columns) {
			deleteColumn(col);
		}
	}
	
	public void fill(Column col, Object o) throws NoSuchColumnInValueTable {
		for (Row row: rows) {
			row.setValue(col, o);
		}
	}
	
	public Object[] getValueArray(Column col) {
		Object[] valueArray = new Object[rows.size()];
		for (int i = 0; i < rows.size(); i++) {
			valueArray[i] = rows.getRow(i).getValue(col);
		}
		return valueArray;
	}
	
	public void fillByArray(Column col, Object[] valueArray) throws NoSuchColumnInValueTable {
		for (int i = 0; i < Math.min(rows.size(), valueArray.length); i++) {
			rows.getRow(i).setValue(col, valueArray[i]);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(columns.toString());
		sb.append("\n");
		sb.append(rows.toString());
		return sb.toString();
	}
	
	public ValueTable clone() {
		ValueTable vt = new ValueTable();
		Columns cols = vt.getColumns();
		for (Column col : this.getColumns()) {
			try {
				cols.add(col.getColumnName(), new ArrayList<Class<?>>(Arrays.asList(col.getArrayOfTypes())));
			} catch (EmptyColumnNameException | EmptyTypeListException
					| NullTypeColumnException | DuplicateNameColumnException e) {
				e.printStackTrace();
			}
		}
		
		Rows rows = vt.getRows();
		for (Row row : this.getRows()) {
			rows.add();
			for (Column col : vt.getColumns()) {
				try {
					rows.getRow(rows.size() - 1).setValue(col, row.getValue(col));
				} catch (NoSuchColumnInValueTable e) {
					e.printStackTrace();
				}	
			}
		}
		return vt;
	}
	
	public ValueTable copyColumns(List<Integer> columnArray) throws CloneNotSupportedException {
		ValueTable newT = this.clone();
		
		return newT;
		
	}
	
	

}
