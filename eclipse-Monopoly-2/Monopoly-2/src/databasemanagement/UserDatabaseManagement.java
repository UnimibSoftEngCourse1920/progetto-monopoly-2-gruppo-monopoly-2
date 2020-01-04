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
	// Database: database_monopoli
	// Tabelle (database_monopoli): user(id, user_name, user_password)
	// Variabili per effettuare la connessione al database
	// (Il server mysql deve essere configurato manualmente)
	private String dbUrl = "jdbc:mysql://localhost:3306/database_monopoli";
	private String dbUsername = "user";
	private String dbPassword = "password";
	
	//Variabili di accesso agli attributi della tabella user(id, user_name, user_password)
	//userTable[1] = "id", userTable[2] = "user_name", userTable[3] = "user_password"
	private String[] userTable = {null, "id", "user_name", "user_password"};
	
	// Oggetti di connessione con il database 
	private Connection connection;
	private Statement stmt;
	
	// La connessione al database sul server mysql viene effettuata instanziando un oggetto di tipo UserDatabaseManagement
	// Costruttore: Crea una connessione con il database e inizializza gli oggetti di connessione
	public UserDatabaseManagement() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver"); 
		this.connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		this.stmt = connection.createStatement();
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*int registration(username:str, password:str)*/
	
	/* Prova ad aggiungere una riga alla tabella user impostando i campi user_name = username, user_password = password */
	
	/* output: WrongInputException -> caratteri non consentiti nei parametri username o password */
	/* output: UserRegistrationException -> username già pressente nel database */
	/* output: ritorna l'id intero corrispondente all'id della riga con user_name = username e user_password = password */

	public int registration(String username, String password) throws SQLException, 
																WrongInputException, 
																UserRegistrationException {
		//Controllo parametro password
		if(!matcherString(password)) throw new WrongInputException();
		
		//Controllo che username non sia presente nella tabella user
		if(getUsername(username) != null) throw new UserRegistrationException();
		
		//Aggiunge username e password all'ultima posizione della tabella generando un nuovo id in automatico
		stmt.executeUpdate ("INSERT INTO user (" + userTable[2] + ", " + userTable[3] + ") " +  
							"VALUES ('" + username +"', '" + password +"')");
		
		return this.getId(username);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*int login(username:str, password:str)*/
	
	/* Verifica che eseista nella tabella user una riga dove il campo user_name = username e user_password = password */
	
	/* output: WrongInputException -> caratteri non consentiti nei parametri username o password */
	/* output: UserLoginException -> nella tabella user non esiste una riga che ha il campo user_name=username */
	/* output: UserLoginException -> nella tabella user la riga che ha il campo user_name=username ha user_password != password */
	/* output: ritorna l'id intero corrispondente all'id della riga con user_name = username e user_password = password */
	
	public int login(String username, String password) throws SQLException, 
																WrongInputException, 
																UserLoginException {
		//Controllo che username sia presente nella tabella user
		if(getUsername(username) == null) throw new UserLoginException();
		
		String correctPass = this.getPassword(username);
		if(correctPass.equals(password)) return this.getId(username);
		
		throw new UserLoginException();
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*int matcherString(string:str)*/
	
	/* Metodo per verificare che il parametro string sia conforme al pattern "[A-Za-z0-9-_]*" */
	/* Dopo questo controllo di solito viene lanciata una WrongInputException nel caso il metodo ritorni false */
	
	public static boolean matcherString(String string) {
		
		if(string==null) return false;
		if(string=="") return false;
		
		Pattern regexp = Pattern.compile("[A-Za-z0-9-_]*");
		Matcher stringTest = regexp.matcher(string);
		boolean ret = stringTest.matches();
		return ret;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*int getUsername(username:str)*/
		
	/* Verifica che eseista nella tabella user una riga dove il campo user_name = username e user_password = password */
		
	/* output: true -> username esiste */
	/* output: false -> username non esiste */

	public String getUsername(String username) throws SQLException, WrongInputException {
		
		if(!matcherString(username)) throw new WrongInputException();
		
		ResultSet userColumn = stmt.executeQuery("SELECT " + userTable[2] + " FROM user");
		while(userColumn.next()) {
			if(userColumn.getString(1).equals(username))
				return username;
		}
		userColumn.close();
		
		return null;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Ritorna la password per un determinato username se esiste
	public String getPassword(String username) throws SQLException, WrongInputException {
		
		if(getUsername(username) == null) throw new WrongInputException();
		
		ResultSet passwordColumn = stmt.executeQuery("SELECT " + userTable[3] + " FROM user WHERE " + 
													 userTable[2] + "='" + username + "'");
		passwordColumn.next();
		String ret = passwordColumn.getString(1);
		passwordColumn.close();
		return ret;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Ritorna l'id di un determinato username se esiste
	public int getId(String username) throws SQLException, WrongInputException {
		
		if(getUsername(username) == null) throw new WrongInputException();
		
		ResultSet idColumn = stmt.executeQuery("SELECT " + userTable[1] + " FROM user WHERE " + 
											   userTable[2] + "='" + username + "'");
		idColumn.next();
		String ret = idColumn.getString(1);
		idColumn.close();
		
		return Integer.parseInt(ret);
	}
	 
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Ritorna il numero di righe nella tabella user
	public int length() throws SQLException {
		
		ResultSet numberRow = stmt.executeQuery("SELECT COUNT(" + userTable[1] + ") FROM user");
		numberRow.next();
		String ret = numberRow.getString(1);
		numberRow.close();
		
		return Integer.parseInt(ret);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Metodo per chiudere la connessione al database e al server mysql
	public void closeUserDatabaseManagement() throws SQLException {
		this.stmt.close();
		this.connection.close();
	}
}
