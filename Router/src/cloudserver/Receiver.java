/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudserver;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.net.*;
import java.io.*;
import javax.crypto.*;
import javax.swing.tree.*;
import java.util.*;
import javax.swing.table.*;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class Receiver {
    NewJFrame1 njf;
    DatagramSocket ds;
    DatagramPacket dp;
    byte data[];
    static String message="";
    CloudResource cr;
    AES ae = new AES();
    
 
    UserProfile up = new UserProfile();
    public static int uindex=0;
NewJFrame1 nf;
String ser[][]= new String[100][2];
public static int srindex=0;
public static int m=0;
String mal[][] = new String[100][2];
public static int mindex=0;
public static int vflag=0;
String CacheFiles[][] = new String[100][5];
public int cindex=0;
public int mport=0;
 String CacheLoc = "D:\\project\\project2\\";
    Receiver( NewJFrame1 nf, int uport)
    {
        njf=nf;
        mport = uport;
        try{
       
        for(int i=0;i<100;i++)
        {
            up.UserNames[i]="";
            up.portno[i]="";
            up.status[i]="";
            
        }
        up.index=0;
         for(int i=0;i<100;i++)
            {
			
             
                for(int j=0;j<5;j++)
                {  CacheFiles[i][j]="";
                 
                }
            }
            
            java.io.File fd = new java.io.File(CacheLoc+"\\"+String.valueOf(uport));
            String fs[] = fd.list();
             javax.swing.table.DefaultTableModel dm = (javax.swing.table.DefaultTableModel)nf.jTable1.getModel();
            for(int i=0;i<fs.length;i++)
            {
                CacheFiles[i][0]= fs[i];
                CacheFiles[i][1]= CacheLoc+String.valueOf(uport);
                java.io.File f = new File(fs[i]);
                long fl =  f.length();
                long lm = f.lastModified();
                CacheFiles[i][2]= String.valueOf(fl);
                CacheFiles[i][3] = String.valueOf(lm);
                CacheFiles[i][4] = "D"+String.valueOf(i);
                cindex = cindex+1;
                
               java.util.Vector v = new java.util.Vector();
               v.addElement(fs[i]);
                
               dm.addRow(v); 
            }
     //   Thread.currentThread().sleep(5000);
        
        System.out.println("Comes");
           
          ds = new DatagramSocket(uport);
            receive();
        }catch(Exception e){e.printStackTrace();}
        
    }
    public void receive()
    {try{

//DatagramSocket ds = new DatagramSocket(90010);
while(true)
{

            System.out.println("Waiting Server");
            byte data[] = new byte[1000];
            DatagramPacket  dp = new DatagramPacket(data,0,data.length);
            System.out.println("Packets constructed");
            
            ds.receive(dp);
            String daddress = String.valueOf(dp.getAddress().getHostAddress());
        int dport = dp.getPort();
        System.out.println("Receied");

       String mes = new  String(dp.getData(),0,dp.getLength()).trim();
        System.out.println(mes);
        String test[] = mes.split("#");
        int flag=0;
        
         
       
        flag=0;
        if(test[0].equals("Lookup"))
        {
            for(int i=0;i<cindex;i++)
            {
            if(CacheFiles[i][0].equals(test[1]))
            {
                flag=1;
            
                System.out.println(CacheFiles[i][0]);
                 java.io.FileInputStream fis = new java.io.FileInputStream(new java.io.File(CacheLoc+"\\"+String.valueOf(mport)+"\\"+CacheFiles[i][0]));
                    String fdata = "";
                    int a=0;
                    while((a=fis.read())!=-1)
                    {
                        char x=       (char)a;
                        fdata= fdata+x;
                    }
                    System.out.println(fdata);
                    String mess = "RResult#";
                    byte data1[] = fdata.getBytes();
                    DatagramPacket dps = new DatagramPacket(data1,0,data1.length);
                    dps.setAddress(dp.getAddress());
                    dps.setPort(dp.getPort());
                    ds.send(dps);
                    
                    
                javax.swing.table.DefaultTableModel dm = (javax.swing.table.DefaultTableModel)njf.jTable2.getModel();
               java.util.Vector v = new java.util.Vector();
               v.addElement(test[1]);
               
               v.addElement(System.currentTimeMillis());
               v.addElement("Read");
               dm.addRow(v);
            }
            }
        }
        if(flag==1)
            continue;
        
       if(test[0].equals("Data"))
       {
           
           String vadd= dp.getAddress().getHostAddress();
           String vport = String.valueOf(dp.getPort());
           for(int i=0;i<mindex;i++)
           {
               if(mal[i][0].equals(vadd) && mal[i][1].equals(vport))
               {
           
            javax.swing.table.DefaultTableModel dm = (javax.swing.table.DefaultTableModel)njf.jTable1.getModel();
               java.util.Vector v = new java.util.Vector();
               v.addElement(dp.getAddress().getHostAddress());
               v.addElement(dp.getPort());
               v.addElement(test[1]);
               v.addElement(System.currentTimeMillis());
               v.addElement("Dropped");
               dm.addRow(v);
               vflag=1;
               }
           
           }
           if(vflag==1)
               continue;
           
           char dat[] = test[1].toCharArray();
            
           for(int i=0;i<dat.length;i++)
           {
               if(Character.isDigit(dat[i]) || Character.isAlphabetic(dat[i]))
               {
                   System.out.println("Genuine characterr");
                   
               }
               else
               {
                   m=1;
                   System.out.println("Identified Malicious");
               }
           }
           if(m==1)
                {
                    
                    up.UserNames[up.index]=dp.getAddress().getHostAddress();
                    up.portno[up.index]= String.valueOf(dp.getPort());
                    up.status[up.index]="m";
                    up.index=up.index+1;
                    String mess = "Info"+"#"+String.valueOf(dp.getAddress().getHostAddress())+"#"+String.valueOf(dp.getPort())+"#"+test[1];
                    
                   byte data1[] = mess.getBytes();
                
            DatagramPacket  dp1 = new DatagramPacket(data1,0,data1.length);  
                
                dp1.setAddress(java.net.InetAddress.getByName("127.0.0.1"));
                dp1.setPort(9000);
                ds.send(dp1);
                
                javax.swing.table.DefaultTableModel dm = (javax.swing.table.DefaultTableModel)njf.jTable2.getModel();
               java.util.Vector v = new java.util.Vector();
               v.addElement(dp.getAddress().getHostAddress());
               v.addElement(dp.getPort());
               v.addElement(test[1]);
               v.addElement("m");
               dm.addRow(v);
                javax.swing.table.DefaultTableModel dm1 = (javax.swing.table.DefaultTableModel)njf.jTable1.getModel();
               java.util.Vector v1 = new java.util.Vector();
               v1.addElement(dp.getAddress().getHostAddress());
               v1.addElement(dp.getPort());
               v1.addElement(test[1]);
               v1.addElement(System.currentTimeMillis());
               v1.addElement("Allowed");
               dm1.addRow(v1);
                }
           else
           {
               javax.swing.table.DefaultTableModel dm = (javax.swing.table.DefaultTableModel)njf.jTable1.getModel();
               java.util.Vector v = new java.util.Vector();
               v.addElement(dp.getAddress().getHostAddress());
               v.addElement(dp.getPort());
               v.addElement(test[1]);
               v.addElement(System.currentTimeMillis());
               v.addElement("Allowed");
               dm.addRow(v);
               
                String mess = "Data"+"#"+String.valueOf(dp.getAddress().getHostAddress())+"#"+String.valueOf(dp.getPort())+"#"+test[1];
                byte datak[] = mess.getBytes();
                //datak=mess.getBytes();
                DatagramPacket dps = new DatagramPacket(datak,0,datak.length);
               dps.setAddress(java.net.InetAddress.getByName("127.0.0.1"));
                dps.setPort(9000);
                ds.send(dps);
                
           }
       }
System.out.println("Continuing");
        }
     }catch(Exception e){	
e.printStackTrace();
System.out.println(e);	

}
    
    }
}
