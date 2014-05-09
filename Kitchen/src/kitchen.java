import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class kitchen extends JFrame
{
    static GridBagConstraints c = new GridBagConstraints();
    
    private final int width = 800;
    private final int height = 600;
    
    static JPanel panel = new JPanel(new GridBagLayout());
    static JScrollPane scroll = new JScrollPane(panel);
    
    public kitchen()
    {
        format();
    }
    
    void format()
    {
        setTitle("Kitchen");
        setSize(width, height);
        setResizable(false);
        
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 1; c.gridy = 1;
        
        scroll.add(panel);
        scroll.setViewportView(panel);
        
        add(scroll, BorderLayout.CENTER);
    }
    
    static void order(String order, int x)	//adds and manipulates orders
    {
    	final JButton expand = new JButton("+");			//button used to expand/collapse text field
    	expand.setPreferredSize(new Dimension(30, 40));
    	c.anchor = GridBagConstraints.NORTHWEST;
    	c.weightx = 0.1;
    	c.gridx = 1;
    	c.gridy = x+1;
    	c.gridwidth = 40;
    	panel.add(expand, c); 
    	String orderFormatted = String.format("Table No# %d ; Order = %s", x,order);
    	
    	final JTextArea neworder = new JTextArea(orderFormatted);	//Text area for orders
    	neworder.setColumns(40); 	
    	neworder.setLineWrap(true);	//used to wrap text when string is too long
    	neworder.setMaximumSize(new Dimension(1, 40));	//sets length of text field
    	neworder.setPreferredSize(new Dimension(1, 40));
    	neworder.setEditable(false);	//stops users from editing text
    	c.gridx = GridBagConstraints.RELATIVE;	
    	panel.add(neworder, c);
    	
    	JButton complete = new JButton("complete");	//button used to complete order
    	complete.setPreferredSize(new Dimension(80, 40));
    	c.anchor = GridBagConstraints.EAST;
    	panel.add(complete, c);
    	
    	expand.addActionListener( new ActionListener()
    	{
    	    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)	//action listener for expand/collapse button
    	    {
    	        int colum = e.getSource().hashCode();	//returns a code for which instance of button pressed
    	        for(int x=0; x<panel.getComponentCount(); x++)	//loops through and finds which button matches code
    	        {
    	        	if(panel.getComponent(x).hashCode() == colum)
    	        	{
    	        		colum = x + 1;	//references text field component next to button pressed
    	        		break;
    	        	}
    	        }
    	        if(panel.getComponent(colum).isMaximumSizeSet() == true)	//expands field
    	        {
    	        	panel.getComponent(colum).setMaximumSize(null);	//removes constraints
    	        	panel.getComponent(colum).setPreferredSize(null);
    	        	panel.getComponent(colum).resize(new Dimension(5, 40));	//refreshes the size on screen
    	        }
    	        else	//collapses field
    	        {
    	        	panel.getComponent(colum).setMaximumSize(new Dimension(1, 40));		//add constraints
    	        	panel.getComponent(colum).setPreferredSize(new Dimension(1, 40));
    	        	panel.getComponent(colum).resize(new Dimension(1, 40));	//refreshes the size on the screen
    	        }
    	    }
    	});
    	
    	complete.addActionListener( new ActionListener()	//action listener for complete button
    	{
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	int colum = e.getSource().hashCode();	//returns a code for which instance of button pressed
    	        for(int x=0; x<panel.getComponentCount(); x++)	//loops through and finds which button matches code
    	        {
    	        	if(panel.getComponent(x).hashCode() == colum)	//removes completed order
    	        	{
    	        		colum = x-2;	//references collapse button in field
    	        		break;
    	        	}
    	        }
    	        
    	        // send Alerts to server which then sends it to server .
    	    	SendAlert sendAlert = new SendAlert(neworder.getText());
    			System.out.println(neworder.getText());
    			new Thread(sendAlert).start();
    	        panel.remove(panel.getComponent(colum));	//removes collapse button
    	        panel.remove(panel.getComponent(colum));	//removes text field
    	        panel.remove(panel.getComponent(colum));	//removes complete button
    	        panel.revalidate();		
    	    	panel.repaint();
    	    }
    	});
    	
    	panel.revalidate();
    	panel.repaint();
    }
 
}
