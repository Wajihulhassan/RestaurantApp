package servers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendOrder implements Runnable {
	String order;
	public SendOrder(String o){
		order = o;
	}
	@Override
	public void run() {
        Socket socket = null;
		try {
			System.out.println("Trying to send order to kitchen");
			socket = new Socket(GlobalV.KitchenIp,GlobalV.sendingPortOrders);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			out.println(order);
			System.out.print("order sent To Kitchen");
         
}
		
	}


