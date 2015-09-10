package twoDimensionalStructures;

import java.util.ListIterator;

import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;

public class Example {

	public static void main(String[] args) throws ClassNotFoundException, EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException  {
		// TODO Auto-generated method stub
		
		ValueTable vt = new ValueTable();
		ColumnsList cols = vt.columns;
		cols.add("1", Class.forName("java.lang.String"));
		cols.add("2", Class.forName("java.lang.String"));
		System.out.println(vt.columns.size());
		
		//ListIterator<String> li = vt.columns.listIterator();
		//li.next();
		//li.remove();
			
		System.out.println(vt.columns.size());

	}

}
