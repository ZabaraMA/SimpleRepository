package twoDimensionalStructures.Exceptions;

public class NoSuchColumnInValueTable extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchColumnInValueTable(String columnName) {super("Column '" + columnName + "' not exists!");} 		
	
}