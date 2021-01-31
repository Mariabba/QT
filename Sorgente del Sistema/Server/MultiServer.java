package server;
/**
 * @author Marianna Abbattista
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
	private int PORT = 8080;
	/**
	 * Costruttore di classe. Inizializza la porta ed invoca run()
	 * @param port
	 * @throws IOException
	 */
	public MultiServer() throws IOException {
		run();
	}
	/**
	 * Istanzia un oggetto istanza della classe ServerSocket che pone in
	 * attesa di richiesta di connessioni da parte del client. 
	 * Ad ogni nuova richiesta connessione si istanzia ServerOneClient.
	 * @throws IOException
	 */
	private void run() throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server Started");
		try {
			while(true) {
				// Si blocca finchè non si verifica una connessione:
				Socket socket = s.accept();
				try {
					new ServerOneClient(socket);
				} catch(IOException e) {
					socket.close();
				}
			}
		} finally {
			s.close();
		}
	}
	/**
	 * Istanzia un oggetto di tipo multiserver
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new MultiServer();
	}
}
