import twoDimensionalStructures.*;

public class Example {

	public static void main(String[] args) throws ClassNotFoundException, EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException {
		// TODO Auto-generated method stub
		ValueTable vt = new ValueTable();
		ColumnsList cols = vt.columns;
		cols.add("1", Class.forName("java.lang.String"));
		cols.add("2", Class.forName("java.lang.String"));
		System.out.println(vt.columns.size());
		
		
	}

}
