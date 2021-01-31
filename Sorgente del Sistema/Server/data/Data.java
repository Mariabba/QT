package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import database.DbAccess;
import database.EmptySetException;
import database.Example;
import database.NoValueException;
import database.QUERY_TYPE;
import database.TableData;
import database.TableSchema;
/**
 * <p>Description:La classe Data modella l'insieme di transazioni (o tuple)
 * @author Marianna Abbattista
 * @param <T>
 */
public class Data<T> {
	/**
	 * Variabili di classe
	 */
	private List<Example> data = new ArrayList<Example>();
	private int numberOfExamples; // cardinalità dell’insieme di transazioni (numero di righe in data)
	List<T> explanatorySet=new LinkedList<T>(); // una lista degli attributi in ciascuna tupla (schema della tabella di dati)
	/**
	 * Inizializza l'arrayList con transazioni di esempio  
	 * (in questo momento, 14 esempi e 5 attributi come riportato nella tabella di mySQL);
	 * Inizializza explanatorySet creando quattro oggetti di tipo DiscreteAttribute e uno di ContinuosAttribute.
	 * Inizializza numberOfExamples 
	 * @throws EmptyDatasetException 
	 * @throws SQLException 
	 * @throws EmptySetException 
	 * @throws NoValueException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data(String table) throws EmptyDatasetException, SQLException, EmptySetException, NoValueException{
		TableData tableData = new TableData(new DbAccess());
		data = tableData.getDistinctTransazioni(table);
		
		TableSchema tableSchema = new TableSchema(new DbAccess(),table);
		numberOfExamples=data.size();
		
		QUERY_TYPE min,max;
		min = QUERY_TYPE.MIN;
		max = QUERY_TYPE.MAX;
		
		for (int i=0;i<tableSchema.getNumberOfAttributes();i++) {
			if(!tableSchema.getColumn(i).isNumber()) {
				explanatorySet.add((T) new DiscreteAttribute(tableSchema.getColumn(i).getColumnName(),i,(TreeSet)tableData.getDistinctColumnValues(table, tableSchema.getColumn(i))));
			}else {
				explanatorySet.add((T) new ContinuousAttribute(tableSchema.getColumn(i).getColumnName(),i,(float)tableData.getAggregateColumnValue(table,tableSchema.getColumn(i),min),(float)tableData.getAggregateColumnValue(table,tableSchema.getColumn(i),max)));
			}
				
		}
	}
	
	void setNumberOfExamples(int num) {
		numberOfExamples = num;
	}
	/**
	 * restituisce  numberOfExamples 
	 * @return numberOfExamples 
	 */
	public int getNumberOfExamples(){
		return numberOfExamples;
	}
	/**
	 * restituisce  la cardinalità attributeSet
	 * @return cardinalità attributeSet
	 */
	public int getNumberOfExplanatoryAttributes(){
		return explanatorySet.size();
	}
	
	/**
	 * restituisce  lo schema dei dati
	 * @return explanatorySet
	 */
	List<T> getAttributeSchema(){
		return explanatorySet;
	}
	/**
	 * restituisce data[exampleIndex][attributeIndex] 
	 * @param exampleIndex
	 * @param attributeIndex
	 * @return
	 */
	public Object getValue(int exampleIndex, int attributeIndex){
		return data.get(exampleIndex).get(attributeIndex);
	}
	/**
	 * crea una stringa in cui memorizza lo schema della tabella e le transazioni memorizzate in data, 
	 * opportunamente enumerate. Restituisce tale stringa 
	 */
	public String toString() {
		String schemeTable=new String();
		int i;
		for(i=0;i<explanatorySet.size();i++)
			 schemeTable=schemeTable+((Attribute) explanatorySet.get(i)).getName()+",";
		for(i=0;i<data.size();i++) {
			schemeTable=schemeTable+"\n"+(i+1)+":";
			for(int j=0;j<explanatorySet.size();j++) {
				schemeTable=schemeTable+getValue(i,j)+",";
			}
		}
		return schemeTable;
	}
		
	/**
	 * Crea e un istanza di Tuple che modelli la  transazione
	 * con indice di riga index in data.
	 * Restituisce il riferimento a tale istanza.  
	 * @param Index indice di riga
	 * @return tuple
	 */
	@SuppressWarnings("rawtypes")
	public Tuple getItemSet(int Index) {
		Tuple tuple=new Tuple(explanatorySet.size());
		for(int i=0;i<explanatorySet.size();i++) {
			if(explanatorySet.get(i) instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute)explanatorySet.get(i),(String)getValue(Index,i)),i);
			}else if(explanatorySet.get(i) instanceof ContinuousAttribute)
				tuple.add(new ContinuousItem((ContinuousAttribute)explanatorySet.get(i),(Double)getValue(Index,i)),i);
		}
			
		return tuple;
	}
}
