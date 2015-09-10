package twoDimensionalStructures.Exceptions;

public class DuplicateNameColumnException extends Exception {

	private static final long serialVersionUID = 1L;
	public DuplicateNameColumnException(String columnName) {super("Column with name '" + columnName + "' already exists!");} 		
	
}
