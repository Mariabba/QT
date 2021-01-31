package data;
/**
 * @author Marianna Abbattista
 */
import java.io.Serializable;

public class ContinuousAttribute extends Attribute implements Serializable{
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
	private double max;
	private double min;// rappresentano gli estremi dell'intervallo di valori(dominio) 
	//che l'attributo può realmente assumere
	
	/**
	 * Invoca il costruttore della classe madre e inizializza i membri aggiunti per estensione
	 * @param name	
	 * @param index identificativo numerico
	 * @param min	valore minimo dell'attributo
	 * @param max	valore massimo dell'attributo
	 */
	ContinuousAttribute(String name, int  index, double min, double max) {
		super(name,index);
		this.max=max;
		this.min=min;
	}
	/**
	 * Calcola e restituisce il valore normalizzato del parametro passato in input.
	 * La normalizzazione ha come codominio lo intervallo [0,1]
	 * @param v
	 * @return valore normalizzato
	 */
	double getScaledValue(double v) {
		return (v-min)/(max-min);
	}
}
