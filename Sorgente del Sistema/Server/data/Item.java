package data;
/**
 * @author Marianna Abbattista
 */
import java.io.Serializable;

public abstract class Item implements Serializable {
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
	Attribute attribute; //attributo coinvolto nell'item
	Object value; //valore assegnato all'attributo
	
	/**
	 * Inizializza i valori dei membri attribute
	 * @param attribute
	 * @param value
	 */
	Item(Attribute attribute,Object value){
		this.attribute = attribute;
		this.value = value;
	}
	
	/**
	 * restituisce attribute
	 * @return attribute
	 */
	Attribute getAttribute() {
		return attribute;
	}
	
	/**
	 * Restituisce value
	 * @return value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Restituisce value
	 * @return value
	 */
	public String toString() {
		return getValue().toString();
	}
	
	abstract double distance(Object a);
}
