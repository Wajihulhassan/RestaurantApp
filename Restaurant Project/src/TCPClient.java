import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String argv[]) throws Exception {
        String request;
        String response;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while(true){
        	request = null;
        	System.out.print("Enter input: ");
	        	request = inFromUser.readLine() + "\r";
	        outToServer.writeBytes(request);
	        System.out.println("Written");
	        //response = inFromServer.readLine();
	        System.out.println("Recieved");

	        if (request.equals("quit\r")){
	        	System.out.println("Goodbye!");
	        	break;
	        }
        }
        clientSocket.close();
    }
}
