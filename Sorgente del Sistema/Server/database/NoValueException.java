package database;

/**
 * @author Marianna Abbattista
 *
 *Modella l’assenza di un valore all’interno di un resultset
 */
public class NoValueException extends Exception{
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttori della classe NoValueException
	 */
	NoValueException(){
		super("Valore non trovato all'interno del resultset");
	}

	public NoValueException(String string) {
		super(string);
	}
}

