package me.istrate.restaurantapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class ListenServer implements Runnable{

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			ServerSocket socket =null;
			try {
				 socket = new ServerSocket(GlobalV.listeningPortAlerts);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Socket alertSocket = null;
			try {
				Log.i("Server ","Waiting for alerts");
				alertSocket = socket.accept();
				Log.i("Server", "connectin recieved from the server");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader input=null;
			try {
				input = new BufferedReader(new InputStreamReader(alertSocket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				try {
					String read = input.readLine();
					Log.i("Alert", read);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
	}

}
