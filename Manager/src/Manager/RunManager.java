/******************************************************
 * Authors:                                           *
 *   Andy Pritt                                       *
 *   Bren Pittsenbarger                               *
 *   Jon Kade                                         *
 *   Jacob Antoine                                    *
 *                                                    *
 * Professor: Lonnie Bowe                             *
 * Due Date: May 2014                                 *
 * Program Description: The RestaurantManager program *
 *  simulates a terminal that would be used by the    *
 *  manager of Doofenschmirtz Eatery Inc. The user    *
 *  must log in to use the terminal by providing a    *
 *  correct username/password combination. Once       *
 *  logged in, the user has access to several         *
 *  managerial options for the restaurant, such as:   *
 *  add or remove an employee, change their username  *
 *  or password, and query the database for           *
 *  restaurant trends.                                *
 *                                                    *
 * Class Description: The RestaurantManager class     *
 *  generates the "Home" frame, which prompts the     *
 *  for the username/password combination required to *
 *  log into the terminal as a manager.               *
 ******************************************************/ 
package Manager;

import javax.swing.*;

public class RunManager
{
    public static void main(String[] args) 
    {
        ManagerHome home = new ManagerHome();
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }
}