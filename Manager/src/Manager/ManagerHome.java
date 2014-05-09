/**************************************************
 * The "Home" class creates the frame for         *
 *  logging into the terminal as the manager      *
 *  of the restaurant. The username and password  * 
 *  are hard-coded as "admin" and "password" by   *
 *  default, but can be changed once logged in.   *
 *  If this panel is closed, the program ends.    *
 *  If an incorrect username/password combination *
 *  combination is entered, the "IncorrectLogin"  *
 *  class is generated. If the user logs in       *
 *  correctly, the "MainMenu" class is generated. *
 **************************************************/
package Manager;


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ManagerHome extends JFrame 
{
    
    //Create components
    GridBagConstraints c = new GridBagConstraints(); 
    
    private final int width = 640;
    private final int height = 320;
    public String username = "admin";        //Hardcoded default manager username
    public String password = "password";     //Hardcoded default manager password (Very secure)
    
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel logoPanel = new JPanel(); //The logo has its own panel
   
    
    
    JLabel logoLbl = new JLabel();  
    JLabel titleLbl = new JLabel("Manager Login Terminal");
    JLabel usernameLbl = new JLabel("Username: ");
    JTextField usernameFld = new JTextField(15);
    JLabel passwordLbl = new JLabel("Password: ");
    JPasswordField passwordFld = new JPasswordField(15);
    JButton loginBtn = new JButton("Login"); 
    
    ActionListener loginLsnr = new ClickListenerLogin();

    //Constructor
    public ManagerHome()
    {
        format();
        addLogo();
    }
    
    //Adds the logo to the top of the frame
    private void addLogo()
    {
        logoLbl.setIcon(new ImageIcon("content\\logo.png"));
        logoPanel.add(logoLbl);
        
        add(logoPanel, BorderLayout.NORTH);
    }

    //Formats the components on the panel, except for the logo
    private void format()
    {
        setTitle("Manager Login");
        setSize(width, height);
        setResizable(false);
                
        c.insets = new Insets(5, 0, 30, 0);
        
        c.gridx = 1; c.gridy = 0;
        mainPanel.add(titleLbl, c);
        
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0; c.gridy = 1;
        mainPanel.add(usernameLbl, c);
        
        c.gridx = 1; c.gridy = 1;
        mainPanel.add(usernameFld, c);
        
        c.gridx = 0; c.gridy = 2;
        mainPanel.add(passwordLbl, c);
        
        c.gridx = 1; c.gridy = 2;
        mainPanel.add(passwordFld, c);
        
        c.gridx = 1; c.gridy = 3;
        mainPanel.add(loginBtn, c);
        loginBtn.addActionListener(loginLsnr);
        
        add(mainPanel, BorderLayout.SOUTH);
    }
    
    //Listener for the Login button
    public class ClickListenerLogin implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            //DB STUFF
        	
        	try {
				if (sendAuthentication())
       {
				//dispose current panel
				dispose();
				//You logged in as the manager
				 ManagerMainMenu menu = new ManagerMainMenu();
				 	menu.setVisible(true);
       }
       else
       {
				 //Your username or password was wrong
					             ManagerIncorrectLogin badLogin = new ManagerIncorrectLogin();
					             badLogin.setVisible(true);
       }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
        	
         usernameFld.setText("");
         passwordFld.setText(""); 
        	
        	
        }
    }
    public boolean sendAuthentication() throws IOException{
    	String str = new String("login"+ "," + usernameFld.getText()+ ","+ passwordFld.getText());
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
}