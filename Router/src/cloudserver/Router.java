/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package cloudserver;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Router {

    public static void main(String[] args) {
        System.out.println("Hello World!");
         String s=(String)   JOptionPane.showInputDialog(new JFrame(),"Specify Your Port","Enter",0);
         
        NewJFrame1 nf = new NewJFrame1();
nf.setVisible(true);
        Receiver r = new Receiver(nf,Integer.parseInt(s));
    }
}
