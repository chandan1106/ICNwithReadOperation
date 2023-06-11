/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudclient;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author INTEL
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         // String s=(String)   JOptionPane.showInputDialog(new JFrame(),"Specify Your Port","Enter",0);
         
        NewJFrame1 nf = new NewJFrame1(10000);
nf.setVisible(true);
//nf.myport =Integer.parseInt(s);
       // Receiver r = new Receiver(nf,Integer.parseInt(s));
        
    }

}
