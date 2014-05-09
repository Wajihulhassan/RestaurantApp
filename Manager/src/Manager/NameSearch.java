package Manager;

import javax.swing.*;

import org.sqlite.SQLite;

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

public class NameSearch extends JDialog 
{
    //Create components
    GridBagConstraints c = new GridBagConstraints();
    
    private final int width = 300;
    private final int height = 150;
    
    JPanel panel = new JPanel(new GridBagLayout());
    String[] userInfo = new String[5];

    JLabel name_Lbl = new JLabel("Name : ");
    JLabel nameLbl = new JLabel();
    JLabel job_Lbl = new JLabel("Job : ");
    JLabel jobLbl = new JLabel();
    JLabel password_Lbl = new JLabel("Password : ");
    JLabel passwordLbl = new JLabel();

    JButton okBtn = new JButton("OK"); 
    ActionListener okLsnr = new ClkLsnrOk();
    
    
    
    public NameSearch (String inName)
    {
    	try {
			userInfo = sendNameSearch(inName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        	nameLbl.setText(userInfo[0]);
    		jobLbl.setText(userInfo[1]);
    		passwordLbl.setText(userInfo[2]);
    		format();
        
        
    }
    

    private void format()
    {
        setTitle("Search Results");
        setSize(width, height);
        setResizable(false);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        
        c.insets = new Insets(5, 5, 5, 5);
        
        c.gridx = 0; c.gridy = 0;
        panel.add(name_Lbl, c);
        
        c.gridx = 1; c.gridy = 0;
        panel.add(nameLbl, c);
        
        c.gridx = 0; c.gridy = 1;
        panel.add(job_Lbl, c);
        
        c.gridx = 1; c.gridy = 1;
        panel.add(jobLbl, c);
        
        c.gridx = 0; c.gridy = 2;
        panel.add(password_Lbl, c);
        
        c.gridx = 1; c.gridy = 2;
        panel.add(passwordLbl, c);

        
        c.gridx = 1; c.gridy = 3;
        panel.add(okBtn, c);
        okBtn.addActionListener(okLsnr);
        
        add(panel, BorderLayout.CENTER);
    }
    
    public class ClkLsnrOk implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            setVisible(false);
            dispose();
        }
    }
    public String[] sendNameSearch(String s) throws IOException{
    	String str = new String("namesearch"+ "," + s );
    	System.out.println(str);
    	InetAddress serverAddr= null;
    	try {
			serverAddr= InetAddress.getByName(GlobalV.serverIP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
        Socket socket = null;
		try {
			System.out.println("sending name search server");
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
			System.out.print("namesearch sent");
			BufferedReader in = null;
			try {
				in = new BufferedReader(
				        new InputStreamReader(socket.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String input = " ";
			try {
				input = in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String[] user_info = new String[3];
			user_info[0]=" ";
			System.out.println(input);
			user_info = input.split(",");
			socket.close();
			return user_info;
    }
}

