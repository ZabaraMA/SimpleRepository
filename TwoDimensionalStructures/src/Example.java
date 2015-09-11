import twoDimensionalStructures.*;
import twoDimensionalStructures.Exceptions.DuplicateNameColumnException;
import twoDimensionalStructures.Exceptions.EmptyColumnNameException;
import twoDimensionalStructures.Exceptions.EmptyTypeListException;
import twoDimensionalStructures.Exceptions.NoSuchColumnInValueTable;
import twoDimensionalStructures.Exceptions.NullTypeColumnException;

public class Example {

	public static void main(String[] args) throws ClassNotFoundException, EmptyColumnNameException, EmptyTypeListException, NullTypeColumnException, DuplicateNameColumnException, NoSuchColumnInValueTable, CloneNotSupportedException {
		// TODO Auto-generated method stub
		ValueTable vt = new ValueTable();
		Columns cols = vt.getColumns();
		Rows rows = vt.getRows();
		
		cols.add("1", String.class);
		cols.add("2", String.class);
		
		rows.add();
		rows.add();
		
		
		vt.fill(cols.getColumn(1), "100");
		vt.fill(cols.getColumn(0), "200");
		rows.add(2);
		System.out.println(rows.size());
		//cols.remove(0);
		//ListIterator<String> li = vt.columns.listIterator();
		//li.next();
		//li.remove();
		
		ValueTable newTable = vt.copyColumns(null);
			
		System.out.println(vt.toString());
		
		System.out.println(vt.clone().toString());
	}

}
