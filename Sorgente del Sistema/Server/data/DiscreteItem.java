package data;
/**
 * @author Marianna Abbattista
 */
import java.io.Serializable;

public class DiscreteItem extends Item implements Serializable{
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Invoca il costruttore della classe madre e inizializza i 
	 * membri attribute e value con il parametro in input
	 * @param attribute
	 * @param value
	 */
	DiscreteItem(DiscreteAttribute<?> attribute,String value){
		super(attribute,value);
	}
	
	/**
	 * Restituisce 0 se value è uguale ad a, altrimenti 1
	 * @return 0 
	 * @return 1
	 */
	double distance(Object a) {
		if(getValue().toString().contains(a.toString()))
			return 0;
		else 
			return 1;
	}
}
