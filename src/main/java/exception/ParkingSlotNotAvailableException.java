package exception;

public class ParkingSlotNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ParkingSlotNotAvailableException() {
		super();
	}

	public ParkingSlotNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParkingSlotNotAvailableException(String message) {
		super(message);
	}
}
