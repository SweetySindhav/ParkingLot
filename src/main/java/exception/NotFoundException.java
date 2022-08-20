package exception;

public class NotFoundException extends Exception {
	public NotFoundException(final String errorMessage)
	{
		super(errorMessage);
	}
	
	public NotFoundException(final String errorMsg, final Throwable errorObject) 
	{
		super(errorMsg, errorObject);
	}
	private static final long serialVersionUID = 1L;
}
