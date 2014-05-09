package Manager;

import javax.swing.*;

import Manager.ChangeHostStaff.ClickListenerAddStaff;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChangeWaitStaff extends JDialog 
{
    //Create components
    GridBagConstraints c = new GridBagConstraints();
    
    private final int width = 680;
    private final int height = 300;
    
    JPanel logoPanel = new JPanel();
    JPanel mainPanel = new JPanel(new GridBagLayout());

    JLabel logoLbl = new JLabel();
    JLabel waiterLbl = new JLabel("Input the Staff's Username:");
    JTextField waiterFld = new JTextField(15);
    
    JButton addBtn = new JButton("Add Wait Staff"); 
    JButton removeBtn = new JButton("Remove Wait Staff");
    
   //DB
    ActionListener deleteStaffLsnr = new ClickListenerDeleteStaff();
  //DB STUFF  
    
    public ChangeWaitStaff ()
    {
        format();
        addLogo();
    }
    
    private void format()
    {
        setTitle("Add/Remove Wait Staff");
        setSize(width, height);
        setResizable(false);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0; c.gridy = 0;
        mainPanel.add(waiterLbl, c);
        
        c.gridx = 1; c.gridy = 0;
        mainPanel.add(waiterFld, c);
        

        c.gridx = 1; c.gridy = 1;
        mainPanel.add(removeBtn, c);
        removeBtn.addActionListener(deleteStaffLsnr);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void addLogo()
    {
        logoLbl.setIcon(new ImageIcon("content\\logo.png"));
        logoPanel.add(logoLbl);
        
        add(logoPanel, BorderLayout.NORTH);
    }
    
    //delete button
    public class ClickListenerDeleteStaff implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try {
				sendDeleteUser();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            dispose();
        }
    }
    public void sendDeleteUser() throws IOException{
    	String str = new String("deleteuser"+ "," + waiterFld.getText());
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
}
