package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenToManager implements Runnable {

	@Override
	public void run() {
		while(true){
	    	ServerSocket ManagerServer = null;
		    try {
		        ManagerServer = new ServerSocket(GlobalV.listeningPortManager);
		    } catch (IOException e) {
		        System.err.println("Problemw3 with port " + GlobalV.listeningPortManager);
		        System.exit(1);
		    }
			Socket managerSocket = null;
	        try {
	        	System.out.println("Waiting for Manager");
				managerSocket = ManagerServer.accept();
				 GlobalV.ManagerIP  = managerSocket.getInetAddress();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        System.out.println("String received from the manager.");
	        BufferedReader input = null;
	        PrintWriter out = null;
			try {
				input = new BufferedReader(
						new InputStreamReader(managerSocket.getInputStream()));
				out = new PrintWriter(managerSocket.getOutputStream(), true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String str=null;
			try {
				str = input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(str);
			
			String[] tokens = str.split(",");
			if(tokens[0].equals("login")){
				System.out.println("Login authentication received");
				if(SQLiteJDBC.authenticateManager(tokens[1], tokens[2])){
					System.out.println("user authenticated");
					out.println("true");
				}else{
					System.out.println("user not authenticated");
					out.println("false");
				}
			}
			if(tokens[0].equals("adduser")){
				SQLiteJDBC.insertUser(tokens[1], tokens[2], tokens[3]);
				
			}
			if(tokens[0].equals("namesearch")){
				String[] info = SQLiteJDBC.nameSearch(tokens[1]);
				if(info[0] != ""){
					String infoToSend = new String(info[0]+","+info[1]+","+info[2]);
					out.println(infoToSend);
				}
				else{
					out.println("NULL" + "," + "NULL" + "," + "NULL");
				}
			}
			if(tokens[0].equals("jobsearch")){
				String[] info = SQLiteJDBC.jobSearch(tokens[1]);
				if(info[0] != ""){
					String infoToSend = new String(info[0]+","+info[1]+","+info[2]);
					out.println(infoToSend);
				}
				else{
					out.println("NULL" + "," + "NULL" + "," + "NULL");
				}
			}
			
			if(tokens[0].equals("deleteuser")){
				
				SQLiteJDBC.deleteUser(tokens[1]);
			}
			// Depending on string received do stuff on server .
			try {
				ManagerServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
		
	}
	public void sendResponse(String str){
		
		
	}

}
