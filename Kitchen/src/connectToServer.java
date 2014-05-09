import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

// Connect to server to get my IP and sends order to kitchen
public class connectToServer implements Runnable {
	@Override
	public void run() {
    	InetAddress serverAddr = null;
    	try {
			serverAddr = InetAddress.getByName(GlobalV.serverIP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
            Socket socket = null;
			try {
				socket = new Socket(serverAddr,GlobalV.connectingPortServer);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.println("Connected to server .Now can receive order and sends Alerts");

    }
		
}


