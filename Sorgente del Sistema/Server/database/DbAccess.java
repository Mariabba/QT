package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Modella l'accesso al db di MYSQL
 *
 * @author Marianna Abbattista
 */
 
public class DbAccess {
	/**
	 * Variabili di classe
	 */
	private String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	
	private final String DBMS = "jdbc:mysql"; 
	
	private final String SERVER="localhost"; //contiene l’identificativo del server su cui risiede la base di dati (per esempio localhost) 
		
	private final String DATABASE = "mapdb"; //contiene il nome della base di dati */
		
	private final int PORT=3306; 		 //La porta su cui il DBMS MySQL accetta le connessioni 
		
	private final String USER_ID = "MapUser";// contiene il nome dell’utente per l’accesso alla base di dati 
		
	private final String PASSWORD = "map";  // contiene la password di autenticazione per l’utente identificato da  USER_ID 
		
	private Connection conn;                 //gestisce una connessione 
	
	private void initConnection() throws DatabaseConnectionException {
		try {
			
			Class.forName(DRIVER_CLASS_NAME);//impartisce al class loader l'ordine di caricare il driver mysql per la gestione di oggetti driver
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			//inizializzo la connessione riferita da conn
			conn=(Connection)DriverManager.getConnection(DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE,USER_ID,PASSWORD);
		}catch(SQLException ex) {
			throw new DatabaseConnectionException();
		}
	}
	
	/**
	 * Restituisce la connessione
	 * @return conn
	 * @throws DatabaseConnectionException
	 */
	Connection getConnection() throws DatabaseConnectionException{
		this.initConnection();	
		return conn;
	}
	
	/**
	 * Chiude la connessione
	 * @throws SQLException
	 */
	void closeConnection() throws SQLException {
		conn.close();
	}
	
}
