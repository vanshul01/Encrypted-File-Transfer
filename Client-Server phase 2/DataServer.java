import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DataServer {
        private static String[] names ={"vanshul","divyansh","yash","vidhu"};
        private static String[] adjs = {"the gentle","the un-gentle" ,"the overworth", "the urbane"};
        
	private static final int PORT = 9090;
	

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(PORT);
		
		System.out.println("[SERVER] waiting for client connection...");
		Socket client =listener.accept();
		System.out.println("[SERVER] Connected to client! ");
		
		PrintWriter out = new PrintWriter (client.getOutputStream(), true);
                BufferedReader in =new BufferedReader(new InputStreamReader( client.getInputStream()));
               
               try {
                    while (true){
                        String request =in.readLine();
                        if (request.contains("name")){
                             out.println( getRandomName() );
	                 } else {
                             out.println("Type 'tell me a name' to get a random name");
                         }
                    }
 	       } finally {
		
		   out.close();
		   in.close();
	       }	
		
		
    }
    
    public static String getRandomName() {
        String name = names [(int)(Math.random()*names.length)];
        String adj = adjs [(int)(Math.random()*adjs.length)];
        return name + " "+ adj;
    }
}