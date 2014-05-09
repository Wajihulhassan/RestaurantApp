package Manager;

import javax.swing.*;

import Manager.ManagerMainMenu.ClickListenerHostStaff;

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

public class ChangeHostStaff extends JDialog 
{
    //Create components
    GridBagConstraints c = new GridBagConstraints();
    
    private final int width = 680;
    private final int height = 350;
    
    JPanel logoPanel = new JPanel();
    JPanel mainPanel = new JPanel(new GridBagLayout());

    JLabel logoLbl = new JLabel();
    JLabel hostLbl = new JLabel("Input Username:");
    JTextField hostFld = new JTextField(15);
    //added by jay DB
    JLabel jobLbl = new JLabel("Input Job Title:");
    JTextField jobFld = new JTextField(15);
    
    JLabel pwdLbl = new JLabel("Input Password:");
    JTextField pwdFld = new JTextField(15);
    
    JButton addBtn = new JButton("Add Staff To DB"); 
    
    
    //DB
    ActionListener addStaffLsnr = new ClickListenerAddStaff();

    
    public ChangeHostStaff ()
    {
        format();
        addLogo();
    }
    
    private void format()
    {
        setTitle("Add/Remove Host Staff");
        setSize(width, height);
        setResizable(false);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0; c.gridy = 0;
        mainPanel.add(hostLbl, c);
        
        c.gridx = 1; c.gridy = 0;
        mainPanel.add(hostFld, c);
        
        //ADDED BY DB
        c.gridx = 0; c.gridy = 1;
        mainPanel.add(jobLbl, c);
        
        c.gridx = 1; c.gridy = 1;
        mainPanel.add(jobFld, c);
        
        c.gridx = 0; c.gridy = 2;
        mainPanel.add(pwdLbl, c);
        
        c.gridx = 1; c.gridy = 2;
        mainPanel.add(pwdFld, c);
        //db
        
       
        c.gridx = 0; c.gridy = 3;
        mainPanel.add(addBtn, c);
        addBtn.addActionListener(addStaffLsnr);

       
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void addLogo()
    {
        logoLbl.setIcon(new ImageIcon("content\\logo.png"));
        logoPanel.add(logoLbl);
        
        add(logoPanel, BorderLayout.NORTH);
    }
    
    public class ClickListenerAddStaff implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try {
				sendAddUser();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            dispose();
        }
    }
    public void sendAddUser() throws IOException{
    	String str = new String("adduser"+ "," + hostFld.getText()+ ","+  jobFld.getText() + "," + pwdFld.getText());
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
