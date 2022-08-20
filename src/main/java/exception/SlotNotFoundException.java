package exception;

public class SlotNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public SlotNotFoundException() {
		super();
	}

	public SlotNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SlotNotFoundException(String message) {
		super(message);
	}
}
