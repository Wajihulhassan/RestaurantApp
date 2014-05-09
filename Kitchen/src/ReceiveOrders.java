import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class ReceiveOrders implements Runnable {
    ServerSocket receiverSocket = null;
    boolean isStopped = false;
    public kitchen kitchenObj;
    
    
	public ReceiveOrders(kitchen k) {
		kitchenObj = k;
	}
	public void run() {
		while (!isStopped)
		{
			Socket clientSocket = null;
			try{
				this.receiverSocket = new ServerSocket(GlobalV.listeningPortOrders);
			}
			catch (IOException e){
				throw new RuntimeException("Cannot open port " + GlobalV.listeningPortOrders);
			}
			try {
				System.out.println("listening for connection on port " + GlobalV.listeningPortOrders + " at "+ receiverSocket.getLocalSocketAddress()  );
				clientSocket = this.receiverSocket.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String order = input.readLine();
				System.out.println(order);
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(order);
				JSONObject jobj = (JSONObject)obj;
				String O = (String) jobj.get("Order");
				System.out.println(O);
				String t = (String) jobj.get("Table");
				System.out.println(t);
				kitchen.order(O, Integer.parseInt(t));
			}
			catch(IOException e){
				e.printStackTrace();
		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		try {
			receiverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Order Received ");
	}
}



}
