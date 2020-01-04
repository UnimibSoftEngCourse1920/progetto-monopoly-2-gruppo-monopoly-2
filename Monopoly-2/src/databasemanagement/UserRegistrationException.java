package databasemanagement;

public class UserRegistrationException extends Exception { 
		private static final long serialVersionUID = 1L;

		public UserRegistrationException() {
			super("WrongRegistration");
		}

		public UserRegistrationException(String message) {
			super(message);
		}
}
