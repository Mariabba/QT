package Client;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import keyboardinput.Keyboard;



public class MainTest {

	/**
	 * @param args
	 */
	private ObjectOutputStream out;
	private ObjectInputStream in ; // stream con richieste del client
	private int cont=0;
	private final static String ip ="127.0.0.1";
	private final static int port = 8080;
	
	public MainTest(String ip, int port) throws IOException{
		InetAddress addr = InetAddress.getByName(ip); //ip
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, port); //Port
		System.out.println(socket);
		
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());	; // stream con richieste del client
	}
	/**
	 * contatore per ciclo interno do while
	 */
	void seti() {
		this.cont++;
	}
	private int menu(){
		int answer;
		System.out.println("Scegli una opzione");
		do{
			System.out.println("(1) Carica Cluster da File");
			System.out.println("(2) Carica Dati");
			System.out.print("Risposta:");
			answer=Keyboard.readInt();
		}
		while(answer<=0 || answer>2);
		return answer;
		
	}
	
	private String learningFromFile() throws SocketException,ServerException,IOException,ClassNotFoundException{
		out.writeObject(1);
		System.out.print("Nome file:");
		String fileName=Keyboard.readString();
		out.writeObject(fileName);
		String result = (String)in.readObject();
		if(result.equals("OK"))
			return (String)in.readObject();
		else throw new ServerException(result);
		
	}
	private void storeTableFromDb() throws SocketException,ServerException,IOException,ClassNotFoundException{
		out.writeObject(0);
		System.out.print("Nome tabella:");
		String tabName=Keyboard.readString();
		out.writeObject(tabName);
		String result = (String)in.readObject();
		if(!result.equals("OK"))
			throw new ServerException(result);
		
	}
	private String learningFromDbTable() throws SocketException,ServerException,IOException,ClassNotFoundException{
		if(cont!=0) {
			out.writeObject(0);
			in.readObject();
		}
		seti();
		System.out.print("Insert Radius:");
		double r=Keyboard.readDouble();
		out.writeObject(r);
		String result = (String)in.readObject();
		if(result.equals("OK")){
			System.out.println("Clustering output:"+in.readObject());
			out.writeObject(2);
			return storeClusterInFile();
		}
		else throw new ServerException(result);
		
		
	}
	
	private String storeClusterInFile() throws SocketException,ServerException,IOException,ClassNotFoundException{
		System.out.print("Inserisci il filename:");
		String answer=Keyboard.readString();
		out.writeObject(answer);
		String result = (String)in.readObject();
		if(!result.equals("OK"))
			 throw new ServerException(result);
		return result;
		
	}
	public static void main(String[] args) {
		MainTest main=null;
		try{
			main=new MainTest(ip,port);
		}
		catch (IOException e){
			System.out.println(e);
			return;
		}
		
		
		do{
			int menuAnswer=main.menu();
			switch(menuAnswer)
			{
				case 1:
					try {
						String qts=main.learningFromFile();
						System.out.println(qts);
					}
					catch (SocketException e) {
						System.out.println(e);
						return;
					}
					catch (FileNotFoundException e) {
						System.out.println(e);
						return ;
					} catch (IOException e) {
						System.out.println(e);
						return;
					} catch (ClassNotFoundException e) {
						System.out.println(e);
						return;
					}
					catch (ServerException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2: // learning from db
				
					while(true){
						try{
							main.storeTableFromDb();
							break; //esce fuori dal while
						}
						
						catch (SocketException e) {
							System.out.println(e);
							return;
						}
						catch (FileNotFoundException e) {
							System.out.println(e);
							return;
							
						} catch (IOException e) {
							System.out.println(e);
							return;
						} catch (ClassNotFoundException e) {
							System.out.println(e);
							return;
						}
						catch (ServerException e) {
							System.out.println(e.getMessage());
						}
					} //end while [viene fuori dal while con un db (in alternativa il programma termina)
						
					char answer='y';//itera al variare di radius
					do{
						try
						{
							String clusterSet=main.learningFromDbTable();
							System.out.println(clusterSet);				
						}
						catch (SocketException e) {
							System.out.println(e);
							return;
						}
						catch (FileNotFoundException e) {
							System.out.println(e);
							return;
						} 
						catch (ClassNotFoundException e) {
							System.out.println(e);
							return;
						}catch (IOException e) {
							System.out.println(e);
							return;
						}
						catch (ServerException e) {
							System.out.println(e.getMessage());
						}
						System.out.print("Vuoi ripetere l'esecuzione?(y/n)");
						answer=Keyboard.readChar();
					}
					while(answer=='y');
					break; //fine case 2
					default:
					System.out.println("Opzione non valida!");
			}
			
			System.out.print("Vuoi scegliere una nuova operazione da menu?(y/n)");
			char c = Keyboard.readChar();
			if(c !='y')
				break;
			}
		while(true);
		}
	}



