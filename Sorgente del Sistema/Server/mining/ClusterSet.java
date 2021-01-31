package mining;
/**
 * @author Marianna Abbattista
 */
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import data.Data;

public class ClusterSet implements Iterable<Cluster>,Serializable {
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
	private Set<Cluster> C=new TreeSet<Cluster>();
	private String examples;
	/**
	 * Costruttori della classe ClusterSet
	 */
	ClusterSet() {
	}
	
	void add(Cluster c) {
	 C.add(c);
	}
	
	
	/**
	 * Restituisce una stringa fatta da ciascun centroide dell’insieme dei cluster.
	 */
	public String toString() {
		return examples;
	}
	
	/**
	 * Restituisce una stringa  che descriva lo stato di ciascun cluster in C.
	 * @param data
	 * @return str
	 */
	public String toString(Data<?> data ){  
		String str="";
		int i=0;
		Iterator<Cluster> c = C.iterator();
		while(c.hasNext()) {
			str+=(i+1)+":"+c.next().toString(data)+"\n";
			i++;
		}
		examples = str;
		return str;
	}

	@Override
	public Iterator<Cluster> iterator() {
		return C.iterator();
	}
}
