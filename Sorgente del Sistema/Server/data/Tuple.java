package data;
/**
 * @author Marianna Abbattista
 */
import java.io.Serializable;
import java.util.Set;

public class Tuple implements Serializable{
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
	Item[] tuple;
	
	/**
	 * Costruisce l'oggetto riferito da tuple
	 * @param size
	 */
	Tuple(int size){
		tuple = new Item[size];
	}
	
	/**
	 * restituisce la lunghezza di tuple
	 * @return lunghezza tuple
	 */
	public int getLength(){
		return tuple.length;
	}
	
	/**
	 * restituisce lo tem  in posizione i  
	 * @param i
	 * @return i-esimo valore di tuple
	 */
	public Item get(int i){
		return tuple[i];
	}
	
	/**
	 * memorizza c in tuple[i]
	 * @param c
	 * @param i
	 */
	void add(Item c,int i){
		tuple[i] = c;
	}
	
	/**
	 * determina la distanza tra la tupla riferita da obj e la tupla corrente.
	 * La distanza è ottenuta come la somma delle distanze 
	 * tra gli item in posizioni eguali nelle due tuple. 
	 * @param obj
	 * @return dist
	 */
	public double getDistance(Tuple obj){
		double dist=0;
		for(int i=0;i<this.getLength();i++) {
			dist+=this.get(i).distance(obj.get(i));
		}
		return dist;
	}
	
	/**
	 * restituisce la media delle distanze tra la tupla corrente e quelle ottenibili dalle righe 
	 * della matrice in data aventi indice  in clusteredData.
	 * @param data
	 * @param clusteredData
	 * @return p
	 */
	public double avgDistance(Data<?> data,Set<Integer>  clusteredData){  
		double p=0.0,sumD=0.0;   
		Object[] array=clusteredData.toArray();
		for(int i=0;i<clusteredData.size();i++){   
			double d= getDistance(data.getItemSet((int)array[i]));    
			sumD+=d;  
		}   
		p=sumD/clusteredData.size();   
		return p; 
	} 
}
