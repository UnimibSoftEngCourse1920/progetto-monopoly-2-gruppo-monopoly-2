package databasemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDatabaseManagement {
	
	// IP:PORT Server MySQL -> localhost:3306
	// Database utilizzato: database_monopoli
	// Tabelle (database_monopoli): user(id, user_name, user_password)
	
	// Attributi privati per effettuare la connessione al database
	private String dbUrl = "jdbc:mysql://localhost:3306/database_monopoli";
	private String dbUsername = "user";
	private String dbPassword = "password";
	
	//userTable[1]="id", userTable[2]="user_name", userTable[3]="user_password"
	private String[] userTable = {null, "id", "user_name", "user_password"};
	
	
	// Oggetti di connessione con il database 
	private Connection connection;
	private Statement stmt;
	
	// Costruttore: Crea una connessione con il database e inizializza gli oggetti di connessione
	public UserDatabaseManagement() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver"); 
		this.connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		this.stmt = connection.createStatement();
	}
	
	//--------------------------------------------------------------------------------------------------------
	
	// Aggiugne l'utente alla tabella user(id, user_name, user_password) autogenerando l'id e ritorna l'id
	// Se il parametro username corrisponde ad un user_name gia presente nella tabella allora ritorna 0
	public int registration(String username, String password) throws SQLException, 
																WrongInputException, 
																UserRegistrationException {
		//Controllo parametri
		if(!matcherString(username)) throw new WrongInputException();
		if(!matcherString(password)) throw new WrongInputException();
		
		//Ciclio per verificare che nella colonna user_name non ci sia un elemento uguale ad username
		//Se dovesse esserci ritorna 0
		ResultSet userColumn = stmt.executeQuery("SELECT " + userTable[2] + " FROM user");
		while(userColumn.next()) {
			if(userColumn.getString(1).equals(username))
				throw new UserRegistrationException();
		}
		userColumn.close();
		
		//Se non ci sono elementi nella tabella user_name uguali ad username allora aggiungo username e password
		//all'ultima posizione della tabella generando un nuovo id in automatico
		stmt.executeUpdate ("INSERT INTO user (" + userTable[2] + ", " + userTable[3] + ") " +  
							"VALUES ('" + username +"', '" + password +"')");
		
		return this.getUserId(username);
	}
	
	public int login(String username, String password) throws SQLException, 
																WrongInputException, 
																UserLoginException {
		
		if(!matcherString(username)) throw new WrongInputException();
		if(!matcherString(password)) throw new WrongInputException();
		
		String correctPass = this.getUserPassword(username);
		
		if(correctPass.equals(password)) return this.getUserId(username);
		
		throw new UserLoginException();
}
	
	public static boolean matcherString(String string) {
		
		if(string==null) return false;
		
		if(string=="") return false;
		
		Pattern regexp = Pattern.compile("[A-Za-z0-9-_]*");
		Matcher stringTest = regexp.matcher(string);
		boolean ret = stringTest.matches();
		return ret;
	}
	
    //--------------------------------------------------------------------------------------------------------
	
	//Ritorna la password per un determinato username
	public String getUserPassword(String username) throws SQLException,
																 WrongInputException {
		
		if(!matcherString(username)) throw new WrongInputException();
		
		ResultSet passwordColumn = stmt.executeQuery("SELECT " + userTable[3] + " FROM user WHERE " + 
													 userTable[2] + "='" + username + "'");
		passwordColumn.next();
		String ret = passwordColumn.getString(1);
		passwordColumn.close();
		
		return ret;
	}
	
	//--------------------------------------------------------------------------------------------------------
	
	//Ritorna l'id di un determinato username
	public int getUserId(String username) throws SQLException, 
														WrongInputException {
		
		if(!matcherString(username)) throw new WrongInputException();
		
		ResultSet idColumn = stmt.executeQuery("SELECT " + userTable[1] + " FROM user WHERE " + 
											   userTable[2] + "='" + username + "'");
		idColumn.next();
		String ret = idColumn.getString(1);
		idColumn.close();
		
		return Integer.parseInt(ret);
	}
	 
	//--------------------------------------------------------------------------------------------------------
	
	//Ritorna il numero di elementi nella tabella user
	public int numberOfUsers() throws SQLException {
		
		ResultSet numberRow = stmt.executeQuery("SELECT COUNT(" + userTable[1] + ") FROM user");
		numberRow.next();
		String ret = numberRow.getString(1);
		numberRow.close();
		
		return Integer.parseInt(ret);
	}
	
	//--------------------------------------------------------------------------------------------------------
	
	//Chiude la connessione con il database
	public void closeUserDatabaseManagement() throws SQLException {
		this.stmt.close();
		this.connection.close();
	}
	
	//--------------------------------------------------------------------------------------------------------

}
