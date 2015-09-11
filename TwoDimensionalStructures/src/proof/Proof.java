package proof;

import twoDimensionalStructures.*;
import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;

public class Proof {
	public static void main(String[] args) throws EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException  {
		// TODO Auto-generated method stub
		ValueTable vt = new ValueTable();
		Columns cols = vt.getColumns();
		cols.add("1", Integer.class);
		cols.add("2", Integer.class);
		System.out.println(vt.getColumns().size());
		cols.remove(1);
		System.out.println(vt.getColumns().size());
		
		
	}
	
}
