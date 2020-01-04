package databasemanagement;

public class UserLoginException extends Exception { 
	private static final long serialVersionUID = 1L;

	public UserLoginException() {
		super("WrongLogin");
	}

	public UserLoginException(String message) {
		super(message);
	}
}
