package mining;
/**
 * @author Marianna Abbattista
 */
public class ClusteringRadiusException extends Exception {
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttori della classe ClusteringRadiusException
	 */
	ClusteringRadiusException(){
		super("Ci sono tutte le tuple in un solo cluster!");
	}
}
