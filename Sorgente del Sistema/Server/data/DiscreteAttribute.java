package data;
/**
 * @author Marianna Abbattista
 */
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

public class DiscreteAttribute<T> extends Attribute implements Iterable<T>,Serializable {
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
	//private String values[];// array di oggetti String, uno per ciascun valore del dominio discreto. 
	//I  valori del dominio sono memorizzati in values seguendo un ordine lessicografico.
	private TreeSet<T> values= new TreeSet<T>();
	/**
	 * Invoca il costruttore della classe madre e  
	   inizializza il membro values con il parametro in input
	 * @param name 
	 * @param index
	 * @param values
	 */
	DiscreteAttribute(String name, int index, TreeSet<T> values){
		super(name,index);
		for(T s : values)
			this.values.add(s);
	}
	
	/**
	 * numero di valori discreti nel dominio dell'attributo
	 * @return lunghezza di values
	 */
	int getNumberOfDistinctValues(){
		return values.size();
	}
	/**
	 * Restituisce il valore in values[i] 
	 * @param i
	 * @return
	 */
	

	@Override
	public Iterator<T> iterator() {
		return values.iterator();
	}
	
}
