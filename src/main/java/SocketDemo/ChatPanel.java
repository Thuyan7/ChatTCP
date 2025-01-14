/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SocketDemo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author AN
 */
public class ChatPanel extends javax.swing.JPanel {
    Socket socket = null;
    BufferedReader bf = null;
    DataOutputStream os = null;
    OutputThread t = null;
    String sender;
    String receiver;
    public ChatPanel(Socket s, String sender, String receiver) {
        initComponents();
        socket = s;
        this.sender = sender;
        this.receiver = receiver;
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new DataOutputStream(socket.getOutputStream());
            t = new OutputThread(s, txtMessages, sender, receiver);
            t.start();
        } catch (Exception e) {
        }
    }
    
    public JTextArea getTxtMessages()
    {
        return this.txtMessages;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        Send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessages = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Message\n"));
        jPanel1.setLayout(new java.awt.GridLayout());

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        jScrollPane1.setViewportView(txtMessage);

        jPanel1.add(jScrollPane1);

        Send.setText("SEND");
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });
        jPanel1.add(Send);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        txtMessages.setColumns(20);
        txtMessages.setRows(5);
        jScrollPane2.setViewportView(txtMessages);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
        // TODO add your handling code here:
        if (txtMessage.getText().trim().length()==0) return;
        try {
            os.writeBytes(txtMessage.getText());
            os.write(13);
            os.write(10);
            os.flush();
            this.txtMessages.append("\n" + sender +": "+ txtMessage.getText());
            txtMessage.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_SendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Send;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextArea txtMessages;
    // End of variables declaration//GEN-END:variables
}
