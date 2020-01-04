package model.user;

import java.sql.SQLException;

import databasemanagement.UserDatabaseManagement;
import databasemanagement.UserLoginException;
import databasemanagement.UserRegistrationException;
import databasemanagement.WrongInputException;

public class User {
	
	private int id;
	private String username;
	
	//Registrazione
	public String registration(String username, String password) {
		
		String output = "CorrectRegistration";
		
		try {
			UserDatabaseManagement dbUsers = new UserDatabaseManagement();
			int idRegistration = dbUsers.registration(username, password);
			this.username = username;
			this.id=idRegistration;
			dbUsers.closeUserDatabaseManagement();
		} catch (ClassNotFoundException | WrongInputException | UserRegistrationException | SQLException e) {
			//e = WrongRegistration
			//e = WrongInput
			output = e.getMessage();
		}
		return output;
	}
	
	//Login
	public String login(String username, String password) {
		
		String output = "CorrectLogin";
		
		try {
			UserDatabaseManagement dbUsers = new UserDatabaseManagement();
			int idLogin = dbUsers.login(username, password);
			this.id=idLogin;
			this.username=username;
		} catch (ClassNotFoundException | WrongInputException | UserLoginException | SQLException e) {
			//e = WrongLogin
			//e = WrongInput
			output = e.getMessage();
		}		
		return output;
	}
	
	// Metodi GET classe User
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	
	//Metodi SET classe User
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
