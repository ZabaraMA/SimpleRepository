package twoDimensionalStructures.Exceptions;

public class EmptyTypeListException extends Exception {

	private static final long serialVersionUID = 1L;
	public EmptyTypeListException(String columnName) {super("Type list of column '" + columnName + "' is empty!");} 		
	
}
