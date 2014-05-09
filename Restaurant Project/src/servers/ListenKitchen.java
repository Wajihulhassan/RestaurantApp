package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenKitchen implements Runnable {
	public void run() {
	    ServerSocket server = null;
	    try {
	        server = new ServerSocket(GlobalV.listeningPortKitchenCon);
	    } catch (IOException e) {
	        System.err.println("Problemw5 with port "+ GlobalV.listeningPortKitchenCon);
	        System.exit(1);
	    }
		Socket clientSocket = null;
	    try {
	    	System.out.println("listenig for Kitchen to connect");
	        clientSocket = server.accept();
	        GlobalV.KitchenIp  = clientSocket.getInetAddress();
	        System.out.println("Kitchen Connected from " + clientSocket .getInetAddress() + " on port "
	             + clientSocket .getPort() + " to port " + clientSocket .getLocalPort() + " of "
	             + clientSocket .getLocalAddress());
	        // Now listen to alerts from the kitchen;
	        ListenKitchenAlerts listenAlerts = new ListenKitchenAlerts();
	        new Thread(listenAlerts).start();
	    } catch (IOException e) {
	        System.exit(1);
	    }    
	}

}
