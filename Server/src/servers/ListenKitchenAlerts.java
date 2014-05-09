package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenKitchenAlerts implements Runnable {

	@Override
	public void run() {
		while(true){
	    	ServerSocket kitchenAlert = null;
		    try {
		        kitchenAlert = new ServerSocket(GlobalV.listeningPortKitchenAlerts);
		    } catch (IOException e) {
		    	
		        System.err.println("Problemw1 with port " + GlobalV.listeningPortKitchenAlerts);
		        System.exit(1);
		    }
			Socket kitchenAlertSocket = null;
	        try {
	        	System.out.println("Waiting for alerts from Kitchen");
				kitchenAlertSocket = kitchenAlert.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        System.out.println("Alert Received from the kitchen.");
	        BufferedReader input = null;
			try {
				input = new BufferedReader(
						new InputStreamReader(kitchenAlertSocket.getInputStream()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String alert=null;
			try {
				alert = input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SendAlertToAndroid sendAlert = new SendAlertToAndroid(alert);
			new Thread(sendAlert).start();
			
			System.out.println(alert);

			try {
				kitchenAlert.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
		
	}

}
