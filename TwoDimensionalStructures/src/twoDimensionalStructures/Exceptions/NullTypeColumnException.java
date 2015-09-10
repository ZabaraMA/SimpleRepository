package twoDimensionalStructures.Exceptions;

public class NullTypeColumnException extends Exception {

	private static final long serialVersionUID = 1L;
	public NullTypeColumnException(String columnName, int i) {super("Type list of column '" + columnName + "' contains null type (index = '" + i + "') !");} 		
	
}
