package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ListenOrders implements Runnable{
	protected Socket clientSocket = null;
	protected String serverText = null;
	
	public void run() {
		System.out.println("Started listening Orders from the Android ");
		while(true){
	    	ServerSocket AndroidOrders = null;
		    try {
		        AndroidOrders = new ServerSocket(GlobalV.listeningPortAndroidOrders);
		    } catch (IOException e) {
		        System.err.println("Problemw2 with port " + GlobalV.listeningPortAndroidOrders);
		        System.exit(1);
		    }
			Socket ordersSocket = null;
	        try {
	        	System.out.println("Waiting for Orders from the Android");
				ordersSocket = AndroidOrders.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        System.out.println("Order Received from the Android");
	        GlobalV.AndroidIP  = ordersSocket.getInetAddress();
	        System.out.println("Android device Ip address = "+ GlobalV.AndroidIP.toString());
	        BufferedReader input = null;
			try {
				input = new BufferedReader(new InputStreamReader(ordersSocket.getInputStream()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String order=null;
			try {
				order = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SendOrder sendOrder = new SendOrder(order);
			new Thread(sendOrder).start();
			System.out.println(order);
			JSONParser parser = new JSONParser();
			Object obj = null;
			try {
				obj = parser.parse(order);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject jobj = (JSONObject)obj;
			String O = (String) jobj.get("Order");
	
			String t = (String) jobj.get("Table");
			
			// Adding order to database
			SQLiteJDBC.insertOrder(Integer.parseInt(t), O);
			System.out.println("Order sent to Kitchen");

			try {
				ordersSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				AndroidOrders.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	}
}
