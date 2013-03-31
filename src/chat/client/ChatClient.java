/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import chat.itf.IChatClient;
import chat.itf.IChatServer;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karl
 */
class ChatClient extends UnicastRemoteObject implements IChatClient{
    private ArrayList<String> received;
    private Registry registry;
    
    public ChatClient() throws RemoteException{
        this.received = new ArrayList<>();
    }
    
    @Override
    public void connect(String host) throws RemoteException{
        this.registry = LocateRegistry.getRegistry(host, IChatServer.DEFAULT_PORT);
        try {
            ((IChatServer) this.registry.lookup("chat")).register(this);
        } catch (NotBoundException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void send(String msg) throws RemoteException{
        try {
            ((IChatServer) this.registry.lookup("chat")).disparch(msg);
        } catch (NotBoundException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void receive(String msg) throws RemoteException{
        this.received.add(msg);
    }
    
    /**
     * Launch a Chat client.
     * 
     * @param args args the command line arguments.
     */
    public static void main(String[] args){
        try {
            final ChatClient client = new ChatClient();
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
        
            //Create and display the form
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    ChatClientFrame frame = new ChatClientFrame(client);
                    GraphicsConfiguration gc = frame.getGraphicsConfiguration();  
                    Rectangle bounds = gc.getBounds();
                    frame.setLocation((int) ((bounds.width / 2) - (frame.getSize().width / 2)),  
                                      (int) ((bounds.height / 2) - (frame.getSize().height / 2)));
                    frame.setVisible(true);
                }
            });
        } catch (RemoteException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
