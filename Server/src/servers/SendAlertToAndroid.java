package servers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendAlertToAndroid implements Runnable {
	

	String alert;
	public SendAlertToAndroid(String a){
		alert = a;
	}
	@Override
	public void run() {
        Socket socket = null;
		try {
			System.out.println("send Alert to android " + GlobalV.AndroidIP.toString());
			socket = new Socket(GlobalV.AndroidIP,GlobalV.sendingPortAlertsToAndroid);
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
			out.println(alert);
			System.out.print("Alert sent to Android");
         
}
		
	}


