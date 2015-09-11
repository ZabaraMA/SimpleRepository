package twoDimensionalStructures;

import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NoSuchColumnInValueTable;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;

public class Example {

	public static void main(String[] args) throws ClassNotFoundException, EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException, NoSuchColumnInValueTable  {
		// TODO Auto-generated method stub
		
		ValueTable vt = new ValueTable();
		Columns cols = vt.columns;
		cols.add("1", Class.forName("java.lang.String"));
		cols.add("2", Class.forName("java.lang.String"));
		vt.fill(cols.getColumn(1), 100);
		vt.fill(cols.getColumn(0), 200);
		System.out.println(vt.columns.size());
		
		//ListIterator<String> li = vt.columns.listIterator();
		//li.next();
		//li.remove();
			
		System.out.println(vt.toString());

	}

}
