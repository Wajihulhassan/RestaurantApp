package Manager;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChangeUsernamePassword extends JDialog 
{
    //Create components
    GridBagConstraints c = new GridBagConstraints();
    
    private final int width = 680;
    private final int height = 450;
    
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel logoPanel = new JPanel();

    JLabel logoLbl = new JLabel();
    JLabel spaceLbl = new JLabel();
    JLabel space2Lbl = new JLabel();
    JLabel oldUsernameLbl = new JLabel("Old Username:");
    JLabel newUsernameLbl = new JLabel("New Username:");
    JLabel oldPasswordLbl = new JLabel("Old Password:");
    JLabel newPasswordLbl = new JLabel("New Password:"); 
    JLabel cfmPasswordLbl = new JLabel("Confirm New Password:"); 
    JLabel newJobLbl = new JLabel("New Job Title:");
    
    JTextField oldUsernameFld = new JTextField(15);
    JTextField newUsernameFld = new JTextField(15);
    JTextField newJobFld = new JTextField(15);
    
    JPasswordField oldPasswordFld = new JPasswordField(15);
    JPasswordField newPasswordFld = new JPasswordField(15);
    JPasswordField cfmPasswordFld = new JPasswordField(15);

    JButton applyBtn = new JButton("Apply"); 
    
    ActionListener applyLsnr = new ClickListenerApply();
    
    public ChangeUsernamePassword ()
    {
        format();
        addLogo();
    }
    
    private void format()
    {
        setTitle("Change Username/Password");
        setSize(width, height);
        setResizable(false);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        
        c.insets = new Insets(5, 5, 5, 5);
        
        c.gridx = 0; c.gridy = 0;
        mainPanel.add(oldUsernameLbl, c);
        
        c.gridx = 1; c.gridy = 0;
        mainPanel.add(oldUsernameFld, c);
        
        c.gridx = 0; c.gridy = 1;
        mainPanel.add(oldPasswordLbl, c);
        
        c.gridx = 1; c.gridy = 1;
        mainPanel.add(oldPasswordFld, c);
        
        c.gridx = 0; c.gridy = 2;
        mainPanel.add(spaceLbl, c);
        
        c.gridx = 0; c.gridy = 3;
        mainPanel.add(space2Lbl, c);
        
        c.gridx = 0; c.gridy = 4;
        mainPanel.add(newUsernameLbl, c);
        
        c.gridx = 1; c.gridy = 4;
        mainPanel.add(newUsernameFld, c);
        
        c.gridx = 0; c.gridy = 5;
        mainPanel.add(newPasswordLbl, c);
        
        c.gridx = 1; c.gridy = 5;
        mainPanel.add(newPasswordFld, c);
        
        c.gridx = 0; c.gridy = 6;
        mainPanel.add(cfmPasswordLbl, c);
        
        c.gridx = 1; c.gridy = 6;
        mainPanel.add(cfmPasswordFld, c);
        
        c.gridx = 0; c.gridy = 7;
        mainPanel.add(newJobLbl, c);
        
        c.gridx = 1; c.gridy = 7;
        mainPanel.add(newJobFld, c);
        
        c.gridx = 1; c.gridy = 8;
        mainPanel.add(applyBtn, c);
        applyBtn.addActionListener(applyLsnr);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void addLogo()
    {
        logoLbl.setIcon(new ImageIcon("content\\logo.png"));
        logoPanel.add(logoLbl);       
        add(logoPanel, BorderLayout.NORTH);
    }
    
    public class ClickListenerApply implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	try {
				if (sendAuthentication()){
					try {
						sendDeleteUser();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						sendAddUser();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				dispose();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    public boolean sendAuthentication() throws IOException{
    	String str = new String("login"+ "," + oldUsernameFld.getText()+ ","+ oldPasswordFld.getText());
    	System.out.println(str);
    	InetAddress serverAddr= null;
    	try {
			serverAddr= InetAddress.getByName(GlobalV.serverIP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
        Socket socket = null;
		try {
			System.out.println("sending login to server");
			socket = new Socket(serverAddr, GlobalV.sendingPortLogin);
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
			out.println(str);
			System.out.print("login sent");
			BufferedReader in = null;
			try {
				in = new BufferedReader(
				        new InputStreamReader(socket.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String input = null;
			try {
				input = in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(input);
			if(input.equals("true") ){
				socket.close();
				return true;
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	return false;
    }
    public void sendDeleteUser() throws IOException{
    	String str = new String("deleteuser"+ "," + oldUsernameFld.getText());
    	System.out.println(str);
    	InetAddress serverAddr= null;
    	try {
			serverAddr= InetAddress.getByName(GlobalV.serverIP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
        Socket socket = null;
		try {
			System.out.println("sending deleteuser to server");
			socket = new Socket(serverAddr, GlobalV.sendingPortLogin);
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
			out.println(str);
			System.out.print("delete user sent");
			socket.close();
    }
    public void sendAddUser() throws IOException{
    	String str = new String("adduser"+ "," + newUsernameFld.getText()+ ","+  newJobFld.getText() + "," + newPasswordFld.getText());
    	System.out.println(str);
    	InetAddress serverAddr= null;
    	try {
			serverAddr= InetAddress.getByName(GlobalV.serverIP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
        Socket socket = null;
		try {
			System.out.println("sending adduser to server");
			socket = new Socket(serverAddr, GlobalV.sendingPortLogin);
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
			out.println(str);
			System.out.print("adduser sent");
			socket.close();
    }
}