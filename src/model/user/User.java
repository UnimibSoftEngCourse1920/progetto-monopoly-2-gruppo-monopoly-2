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
		
		this.username = username;
		
		String output = "CorrectRegistration";
		
		try {
			UserDatabaseManagement dbUsers = new UserDatabaseManagement();
			int idOut = dbUsers.registration(username, password);
			this.id=idOut;
			dbUsers.closeUserDatabaseManagement();
		} catch (ClassNotFoundException | WrongInputException | UserRegistrationException | SQLException e) {
			//WrongRegistration
			//WrongInput
			output = e.getMessage();
		}
		return output;
	}
	
	//Login
	public String login(String username, String password) {
		
		String output = "CorrectLogin";
		
		try {
			UserDatabaseManagement dbUsers = new UserDatabaseManagement();
			int idOut = dbUsers.login(username, password);
			this.id=idOut;
		} catch (ClassNotFoundException | WrongInputException | UserLoginException | SQLException e) {
			//WrongLogin
			//WrongInput
			output = e.getMessage();
		}
		this.username=username;
		
		return output;
	}

	
	// Metodi GET classe User
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	
	//Metodi POST classe User
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	
	

}
