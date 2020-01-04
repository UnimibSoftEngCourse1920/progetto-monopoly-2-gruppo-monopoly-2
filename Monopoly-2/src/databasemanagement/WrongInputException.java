package databasemanagement;

public class WrongInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public WrongInputException() {
		super("WrongInput");
	}

	public WrongInputException(String message) {
		super(message);
	}
}
