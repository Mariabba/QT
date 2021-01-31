package server;
/**
 * @author Marianna Abbattista
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import data.Data;
import data.EmptyDatasetException;
import database.EmptySetException;
import database.NoValueException;
import mining.ClusteringRadiusException;
import mining.QTMiner;

public class ServerOneClient extends Thread {
	private Socket socket; 
	private ObjectInputStream in;  
	private ObjectOutputStream out; 
	private String table2;
	private int cont=0;
	
	/**
	 * Costruttore di classe. Inizializza gli attributi socket, in e out. Avvia il thread.
	 * @param s
	 * @return 
	 * @throws IOException
	 */
	public ServerOneClient(Socket s) throws IOException{
		socket = s;
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		start();
	}
	/**
	 * Riscrive il metodo run della superclasse Thread al fine di gestire
	 *  le richieste del client.
	 */
	public void run() {
		try {
		while(true) {
			Integer choise = (Integer)in.readObject();
			if(choise.equals(0)) {
				String table = "";			
				if(this.cont == 0) {
					table = (String)in.readObject();
					setTable(table);
					seti();
				}
				out.writeObject("OK");
						double r =(double)in.readObject();
						try {
							@SuppressWarnings("rawtypes")
							Data data =new Data(this.table2);
						
							QTMiner qt=new QTMiner(r);
					
							qt.compute(data);					
							out.writeObject("OK");
							out.writeObject(data.toString()+"\n"+qt.getC().toString(data));
							if(in.readObject().equals(2)) {
								String fileName = (String)in.readObject();
								qt.salva(fileName+".dmp");
								out.writeObject("OK");
							}
						}catch(NoValueException e) {
							out.writeObject(e.getMessage());
						}catch(EmptySetException e) {
							out.writeObject(e.getMessage());
						}catch(SQLException e) {
							out.writeObject(e.getMessage());
						} catch (EmptyDatasetException e) {
							e.printStackTrace();
						} catch (ClusteringRadiusException e) {
							out.writeObject(e.getMessage());
						}
			}else if(choise.equals(1)) {
				try {
					String fileName  = (String)in.readObject();
					QTMiner qt=new QTMiner(fileName+".dmp");
					out.writeObject("OK");
					out.writeObject(qt.getCen());
				}catch(FileNotFoundException e) {
					out.writeObject("Il file inserito e' inesistente");
				}catch(ClassNotFoundException e) {
					out.writeObject("Impossibile leggere il file");
				}
				
			}
		}
	}catch(IOException e) {
		System.out.println("Client chiuso.");
		System.out.println("In attesa del prossimo Client...");
	}catch(ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}finally {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	}
	/**
	 * setta table2 con t
	 */
	void setTable(String t) {
		this.table2 = t;
	}
	/**
	 * getter della tabella utilizzato con ciclo>1
	 * @return table2
	 */
	String getTable() {
		return this.table2;
	}
	/**
	 * contatore per ciclo interno do while
	 */
	void seti() {
		this.cont++;
	}
}
