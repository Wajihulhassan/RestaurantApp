import javax.swing.*;

import java.awt.List;
import java.util.Scanner;

public class kitchenMain 
{

	public static void main(String[] args) 
	{
		int x = 1;
		kitchen Kitchen = new kitchen();
		Kitchen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Kitchen.setVisible(true);
        // Connecting to server to get sending my IP 
        connectToServer connect = new connectToServer();
        new Thread(connect).start();
        // Starting Receiver thread to listen from Server
		ReceiveOrders server = new ReceiveOrders(Kitchen);
		new Thread(server).start();
		
	}

}
