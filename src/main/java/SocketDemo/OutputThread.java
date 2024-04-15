/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author AN
 */
public class OutputThread extends Thread{
    Socket socket;
    JTextArea txt;
    BufferedReader bf ;
    String sender;
    String receiver;
    
    public OutputThread(Socket s, JTextArea txt, String sender, String receiver)
    {
        super();
        this.socket = s;
        this.txt=txt;
        this.sender=sender;
        this.receiver=receiver;
        
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Network Error!");
            System.exit(0);
        }
    }
    
    public void run()
    {
        while(true)
        {
            try {
                if(socket != null)
                {
                    String msg="";
                    if((msg = bf.readLine()) != null && msg.length()>0)
                        txt.append("\n" + receiver +": " +msg);
                }
                sleep(1000);
            } catch (Exception e) {
            }
        }
    }
    
}
