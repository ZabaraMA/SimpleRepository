import twoDimensionalStructures.*;
import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;

public class Example {

	public static void main(String[] args) throws ClassNotFoundException, EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		// TODO Auto-generated method stub
		ValueTable vt = new ValueTable();
		ColumnsList cols = vt.columns;
		cols.add("1", Integer.class);
		cols.add("2", Integer.class);
		System.out.println(vt.columns.size());
		
	}

}
