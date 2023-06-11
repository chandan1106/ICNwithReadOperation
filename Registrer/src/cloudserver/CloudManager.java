/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudserver;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Administrator
 */
public class CloudManager {
    NewJFrame1 njf;
    CloudManager(NewJFrame1 nf)
    {
        njf=nf;
    }
    public void DoHandle()
    {System.out.println("Stat");
    
    
    
        Receiver r = new Receiver(this,njf,"D:\\project\\project2\\Cache");
        r.receive();
    }

}
