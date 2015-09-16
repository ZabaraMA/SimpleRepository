package twoDimensionalStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
	
	public ValueTable clone(List<? extends Column> cl) {
		ValueTable vt = new ValueTable();
		Columns cols = vt.getColumns();
		for (Column col : this.getColumns()) {
			try {
				if ((cl == null) || cl.isEmpty() || cl.contains(col))
				{cols.add(col.getColumnName(), 
						new ArrayList<Class<?>>(Arrays.asList(col.getArrayOfTypes())));};
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
	
	public ValueTable clone() {
		return this.clone(null);
	}
	
	public ValueTable group(List<? extends Column> cl) {

		ValueTable vt = this.clone(cl);
		
		HashSet<Row> colSet = new HashSet<Row>(vt.getRows().rList);
		vt.getRows().rList = new ArrayList<>(colSet);		
		
		return vt;
	}
	
	public void sort(final List<Column> colList, final List<SortType> sortList) {
		
		Collections.sort(rows.rList, new Comparator<Row>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Row r1, Row r2) {
								
				int res = 0;
				for (int i = 0; i < colList.size(); i++) {
					
					int sortInd = 1;
					if (sortList.size() >= (i + 1) && sortList.get(i).equals(SortType.Descending)) {
						sortInd = -1;
					}
					try { 
						Comparable<Object> val1 = (Comparable<Object>) r1.getValue(i);
						Comparable<Object> val2 = (Comparable<Object>) r2.getValue(i);
						res = val1.compareTo(val2);
					}
					catch (Throwable e) {
						int res1 = r1.getValue(i) == null ? 0 : r1.getValue(i).hashCode();
						int res2 = r2.getValue(i) == null ? 0 : r2.getValue(i).hashCode();	
						res = res1 - res2;
					}
					if (res!=0) {return sortInd * res;}
				}
				return res;
			}
		});
	}	
}
