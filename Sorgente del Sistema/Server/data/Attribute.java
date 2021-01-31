package data;

import java.io.Serializable;

/**
 * <p>Description:la classe Attributo modella le entità attributo,
 * non può essere istanziata e ha lo scopo di essere richiamata 
 * come superclasse per una nuova classe.
 * @author Marianna Abbattista
 */
public abstract class Attribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; //nome simbolico
	private int index;   //identificativo numerico dell'attributo
	
	/**
	 * Inizializza il valore dei membri name,index
	 * @param name nome simbolico dell'attributo
	 * @param index identificativo numerico dell'attributo(primo,secondo...attributo della tupla)
	 */
	Attribute(String name,int index){
		this.name=name;
		this.index=index;
	}
	
	/**
	 * Restituisce name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Restituisce index
	 * @return index
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * restituisce la stringa rappresentante lo stato
	 * dell'oggetto.
	 */
	public String toString() {
		return getName();
	}
	
	
}
