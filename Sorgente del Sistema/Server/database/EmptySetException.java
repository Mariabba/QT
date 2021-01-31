package database;

/**
 * Eccezione per modellare la restituzione di un resultset vuoto.
 * @author Marianna Abbattista
 *
 */
public class EmptySetException extends Exception{
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore della classe EmptySetException
	 */
	EmptySetException(){
		super("Resultset vuoto: la tabella inserita potrebbe non esistere all'interno del database.");
	}
}
