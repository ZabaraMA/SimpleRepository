package proof;

import java.util.ArrayList;

import twoDimensionalStructures.*;
import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NoSuchColumnInValueTable;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;

public class Proof {
	public static void main(String[] args) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException, NoSuchColumnInValueTable  {
		// TODO Auto-generated method stub
		ValueTable vt = new ValueTable();
		Columns cols = vt.getColumns();
		cols.add("1", Company.class);
		cols.add("2", Integer.class);
		Rows rows = vt.getRows();
//		rows.add();
//		rows.add();
//		rows.add();
//		rows.add();
	
		rows.add();
		rows.add();
		
		vt.fill(cols.getColumn(1), 111);
				
		//rows.add();
		
		rows.getRow(rows.add()).setValue(0, new Company("ссс"));
		rows.getRow(rows.add()).setValue(0, new Company("bbb"));
		rows.getRow(rows.add()).setValue(0, new Company("bbb"));
		rows.getRow(rows.add()).setValue(0, new Company("aaa"));
		
		vt.fill(cols.getColumn(1), 111);
				
		
		System.out.println(vt.toString());
		
		//ArrayList<Column> lc = new  ArrayList<Column>();
		//lc.add(cols.getColumn(0));
		//System.out.println(vt.group(lc));
		
		ArrayList<Column> colList = new ArrayList<>();
		colList.add(cols.getColumn(1));
		colList.add(cols.getColumn(0));
		ArrayList<SortType> sortList = new ArrayList<>();
		sortList.add(SortType.Descending);
		vt.sort(colList, sortList);
		System.out.println(vt);
			
	}
	
}

class Company implements Comparable<Company> {
	
	String name;

	public Company(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "C[" + name + "]";
	}

	@Override
	public int compareTo(Company o) {
		return this.name.compareTo(o.name);
	}
	
	
	
	
}