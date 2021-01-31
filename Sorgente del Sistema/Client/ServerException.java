package Client;

public class ServerException extends Exception{
	
	private static final long serialVersionUID = 1L;

	ServerException( String result){
		super("Problema con la ricezione file del server.");
	}
}
