package mining;
/**
 * @author Marianna Abbattista
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import data.Data;
import data.Tuple;

public class QTMiner implements Serializable {
	/**
	 * Variabili di classe
	 */
	private static final long serialVersionUID = 1L;
	private ClusterSet C;
	private double radius;
    private String Centroids="";
    private int cont = 1;
	
	/**
	 * Crea l'oggetto array riferito da  C e inizializza radius
	 * con il parametro  passato come input
	 * @param radius
	 */
	public QTMiner(double radius){
		C=new ClusterSet();
		this.radius=radius;
	}
	/**
	 * Apre il file identificato da fileName, legge l'oggetto ivi memorizzato e lo assegna a C.
	 * @param FileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public QTMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
		FileInputStream f = new FileInputStream(fileName);
        ObjectInputStream inStream = new ObjectInputStream(f);
        setCen((String)inStream.readObject());
        inStream.close();
	}
	
	/**
	 * Restituisce C
	 * @return C
	 */
	public ClusterSet getC() {
		return C;
	}
	
	/**
	 * Esegue l’algoritmo QT
	 * @param data
	 * @return numclusters
	 */
	public int compute(Data<?> data) throws ClusteringRadiusException{    
		int numclusters=0;   
		boolean isClustered[]=new boolean[data.getNumberOfExamples()];
		   for(int i=0;i<isClustered.length;i++)
			    isClustered[i]=false; 
		   
		  int countClustered=0;
		   while(countClustered!=data.getNumberOfExamples()) 
			   { 
			    //Ricerca cluster più popoloso
			    Cluster c=buildCandidateCluster(data, isClustered); 
			    C.add(c);    
			    numclusters++; 
		 
			    //Rimuovo tuple clusterizzate da dataset 
		     
			    Iterator<Integer> clusteredTupleId=c.iterator();
			    while(clusteredTupleId.hasNext()){
			      isClustered[clusteredTupleId.next()]=true; 
			    } 
			    countClustered+=c.getSize();
		   } 
   if(numclusters == 1) {
	   throw new ClusteringRadiusException();
   }
   return numclusters;
  } 
  
	/**
	 * costruisce un cluster per ciascuna tupla di data non ancora clusterizzata 
	 * in un cluster di C e restituisce il cluster candidato più popoloso 
	 * @param data
	 * @param isClustered
	 */
	private Cluster buildCandidateCluster(Data<?> data, boolean isClustered[]) {
		Cluster candidateC = null, cl = null;
		int size = 0;
		for (int i = 0; i < isClustered.length; i++) {
			if (isClustered[i] == false) {
				Tuple t = data.getItemSet(i);
				cl = new Cluster(t);
				cl.addData(i);
				for (int j = 0; j < isClustered.length; j++) {
					if ((isClustered[j] == false) && (j != i)) {
						Tuple t2 = data.getItemSet(j);
						double dist = cl.getCentroid().getDistance(t2);
						if (Double.compare(dist, radius) < 1) {
							cl.addData(j);
						}
					}
				}
				if (cl.getSize() > size) {
					candidateC = cl;
					size = cl.getSize();
				}
			}
		}
		setCen(candidateC.getCentroid());
		return candidateC;
	}
	
	/**
	 * Apre il file identificato da fileName e salva l'oggetto riferito da C in tale file. 
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salva(String fileName) throws FileNotFoundException, IOException{
		FileOutputStream outFile = new FileOutputStream (fileName);
        ObjectOutputStream outStream = new ObjectOutputStream (outFile);
        outStream.writeObject(getCen());
        outStream.close();
	}
	/**
	 * Salva i centroidi
	 * @param tuple
	 */
	void setCen(Tuple tuple) {
		String str="";
		for(int i=0;i<tuple.getLength();i++) {
			if(i==0) {
				str = str + getCont() + ")"+ tuple.get(i);
			}else {
				str = str+","+tuple.get(i);
			}	 
		}
		setCont();
		str += "\n";
		this.Centroids += str;
	}
	/**
	 * setta Centroids dai dati caricati
	 * @param o
	 */
    void setCen(String o) {
		this.Centroids=o;
	}
	/**
	 * Richiama i centroidi salvati
	 * @return Centroids
	 */
	public String getCen() {
		return this.Centroids;
	}
	/**
	 * setta cont
	 */
	void setCont() {
		this.cont++;
	}
	/**
	 * prende il valore di cont
	 * @return cont
	 */
	public int getCont() {
		return this.cont;
	}
}
