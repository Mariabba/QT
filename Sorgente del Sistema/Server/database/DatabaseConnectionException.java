package database;

/**
 * Eccezione in caso di fallimento nella connessione al database
 * @author Marianna Abbattista
 *
 */
public class DatabaseConnectionException extends Exception{
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
/**
 * Costruttore della classe DatabaseConnectionException
 */
	DatabaseConnectionException(){
		super("Connessione al database fallita.");
	}
}
