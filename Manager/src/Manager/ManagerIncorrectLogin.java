/*************************************************
 * The "IncorrectLogin" class generates a simple *
 *  frame that tells the user that their         *
 *  username/password combination was incorrect, *
 *  and they should try again.                   *
 *************************************************/
package Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerIncorrectLogin extends JDialog 
{
    //Create components
    GridBagConstraints c = new GridBagConstraints();
    
    private final int width = 210;
    private final int height = 150;
    
    JPanel panel = new JPanel(new GridBagLayout());

    JLabel messageLbl = new JLabel("Incorrect Username or Password");
    JLabel message2Lbl = new JLabel("Try again");
    JButton okBtn = new JButton("OK"); 
    ActionListener okLsnr = new ClkLsnrOk();
    
    public ManagerIncorrectLogin ()
    {
        format();
    }
    
    private void format()
    {
        setTitle("Error");
        setSize(width, height);
        setResizable(false);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0; c.gridy = 0;
        panel.add(messageLbl, c);
        
        c.gridx = 0; c.gridy = 1;
        panel.add(message2Lbl, c);
        
        c.gridx = 0; c.gridy = 2;
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
}
