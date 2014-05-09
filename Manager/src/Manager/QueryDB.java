package Manager;

import javax.swing.*;

import Manager.ChangeUsernamePassword.ClickListenerApply;

import java.awt.*;
import java.awt.event.*;

public class QueryDB extends JDialog 
{
    
    //Create components
    GridBagConstraints c = new GridBagConstraints(); 
    
    private final int width = 680;
    private final int height = 300;
    
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel logoPanel = new JPanel(); //The logo has its own panel

    JLabel logoLbl = new JLabel();  
    JLabel titleLbl = new JLabel("This is the Query screen");
    JLabel namesearchLbl = new JLabel("Search By UserName");
    JLabel jobsearchLbl = new JLabel("Search By Job");

    
    JTextField namesearchFld = new JTextField(15);
    JTextField jobsearchFld = new JTextField(15);

    
    JButton namesearchBtn = new JButton("Search"); 
    JButton jobsearchBtn = new JButton("Search"); 

    ActionListener namesearchLsnr = new NameSearchLsnr();
    ActionListener jobsearchLsnr = new JobSearchLsnr();

    
    


    //Constructor
    public QueryDB()
    {
        addLogo();
        format();
    }
    
    //Adds the logo to the top of the frame
    private void addLogo()
    {
        logoLbl.setIcon(new ImageIcon("content\\logo.png"));
        logoPanel.add(logoLbl);
        
        add(logoPanel, BorderLayout.NORTH);
    }
 
    private void format()
    {
        setTitle("Database Query");
        setSize(width, height);
        setResizable(false);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        
        c.insets = new Insets(5, 5, 5, 5);
        
        c.gridx = 0; c.gridy = 0;
        mainPanel.add(namesearchLbl, c);
        
        c.gridx = 1; c.gridy = 0;
        mainPanel.add(namesearchFld, c);
        
        c.gridx = 2; c.gridy = 0;
        mainPanel.add(namesearchBtn, c);
        namesearchBtn.addActionListener(namesearchLsnr);
        
        c.gridx = 0; c.gridy = 1;
        mainPanel.add(jobsearchLbl, c);
        
        c.gridx = 1; c.gridy = 1;
        mainPanel.add(jobsearchFld, c);
        
        c.gridx = 2; c.gridy = 1;
        mainPanel.add(jobsearchBtn, c);
        jobsearchBtn.addActionListener(jobsearchLsnr);

  
        add(mainPanel, BorderLayout.CENTER);
    }
    
    public class NameSearchLsnr implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	NameSearch search = new NameSearch(namesearchFld.getText());
            search.setVisible(true);
            dispose();
        }
    }
    
    public class JobSearchLsnr implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	JobSearch search = new JobSearch(jobsearchFld.getText());
            search.setVisible(true);
            dispose();
        }
    }
    
}
