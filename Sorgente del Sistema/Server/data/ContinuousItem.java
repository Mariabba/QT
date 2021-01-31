package data;
/**
 * @author Marianna Abbattista
 */
import java.io.Serializable;

public class ContinuousItem extends Item implements Serializable{
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Richiama il costruttore della superclasse
	 * @param attribute
	 * @param value
	 */
	ContinuousItem(Attribute attribute, double value) {
		super(attribute, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Determina la distanza (in valore assoluto) 
	 * tra il valore scalato memorizzato nello item 
	 * corrente (this.getValue()) e quello scalato 
	 * associato al parametro a. 
	 */
	double distance(Object a) {
		ContinuousAttribute x = (ContinuousAttribute)this.getAttribute();
		//return Math.abs(x.getScaledValue((double)this.getValue())-x.getScaledValue(Double.parseDouble(a.toString())));
		return Math.abs(x.getScaledValue((double)this.getValue())-x.getScaledValue((double)((ContinuousItem)a).getValue()));
	}

}