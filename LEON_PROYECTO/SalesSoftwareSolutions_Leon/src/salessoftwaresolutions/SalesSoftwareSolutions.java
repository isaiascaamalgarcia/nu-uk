/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salessoftwaresolutions;

import Login.Login;

/**
 *
 * @author Shary
 */
public class SalesSoftwareSolutions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
            Login login = new Login();
            login.setVisible((true));
            login.setLocationRelativeTo(null);
        } catch (Exception e) {
        }
    }

}
