package exception;

/**
 * Generic exception for general parking lot exceptions.
 */
public class ParkingLotException extends RuntimeException {

  private static final long serialVersionUID = 1L;

public ParkingLotException() {
  }

  public ParkingLotException(String message) {
    super(message);
  }
}
