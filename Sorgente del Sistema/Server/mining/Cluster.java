package mining;
/**
 * @author Marianna Abbattista
 */
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import data.Data;
import data.Tuple;
//import utility.ArraySet;

public class Cluster implements Iterable<Integer>, Comparable<Cluster>,Serializable  {
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;

	private Tuple centroid;

	private Set<Integer> clusteredData;
	/**
	 * Costruttori della classe Cluster
	 * @param centroid
	 */
	Cluster(Tuple centroid){
		this.centroid=centroid;
		clusteredData=new HashSet<Integer>();
		
	}
	/**
	 * Ricava i centroidi	
	 * @return centroid
	 */
	Tuple getCentroid(){
		return centroid;
	}
	/**
	 * return true if the tuple is changing cluster
	 * @param id
	 * @return clusteredData
	 */
	boolean addData(int id){
		return clusteredData.add(id);
		
	}
	
	/**
	 * verifica se una transazione è clusterizzata nell'array corrente
	 * @param id
	 * @return clusteredData
	 */
	boolean contain(int id){
		return clusteredData.contains(id);
	}
	

	/**
	 * remove the tuplethat has changed the cluster
	 * @param id
	 */
	void removeTuple(int id){
		clusteredData.remove(id);
		
	}
	/**
	 * Ricava la grandezza di clusteredData
	 * @return int size
	 */
	int getSize(){
		return clusteredData.size();
	}
	/**
	 * Pone i centroidi in una stringa
	 * @return str
	 */
	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i);
		str+=")";
		return str;	
	}
	/**
	 * pone in una stringa i centroidi, gli esempi e le distanze
	 * @param data
	 * @return str
	 */
	public String toString(Data<?> data){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";
		str+=")\nExamples:\n";
		Object[] array=clusteredData.toArray();
		for(int i=0;i<clusteredData.size();i++){
			str+="[";
			for(int j=0;j<data.getNumberOfExplanatoryAttributes();j++)
				str+=data.getValue((int)array[i], j)+" ";
			str+="] dist="+getCentroid().getDistance(data.getItemSet((int)array[i]))+"\n";
			
		}
		str+="\nAvgDistance="+getCentroid().avgDistance(data, clusteredData);
		return str;
		
	}

	@Override
	public int compareTo(Cluster o) {
		if(o.getSize()<clusteredData.size())
			return -1;
		else
			return 1;
		
	}

	@Override
	public Iterator<Integer> iterator() {
		return clusteredData.iterator();
	}

}
