/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import chat.itf.IChatClient;
import chat.itf.IChatServer;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karl
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer{
    public static Registry RMI_REGISTRY; 
    
    private ArrayList<IChatClient> clients;
    
    public ChatServer() throws RemoteException{
        this.clients = new ArrayList<>();
    }

    @Override
    public void register(IChatClient client) throws RemoteException{
        this.clients.add(client);
    }

    @Override
    public void disparch(String msg) throws RemoteException{
        Collection<IChatClient> disconnected = new ArrayList<>();
        for(Iterator<IChatClient> it = this.clients.iterator(); it.hasNext();){
            IChatClient c = it.next();
            try{
                c.receive(msg);
            } catch(RemoteException ex){
                //Client is unreachable/disconnected.
                disconnected.add(c);
            }
        }
        //Remove the unreachable/disconnected clients
        this.clients.removeAll(disconnected);
    }
    
    /**
     * Register a chat server into the RMIregistry.
     * 
     * @param args args the command line arguments.
     */
    public static void main(String[] args){
        //Create registry. (replace rmiregistry separated process)
        try {
            RMI_REGISTRY = LocateRegistry.createRegistry(IChatServer.DEFAULT_PORT);
            
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
                java.util.logging.Logger.getLogger(ChatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            
            //Create and display the form
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ChatServerFrame frame = new ChatServerFrame();
                    GraphicsConfiguration gc = frame.getGraphicsConfiguration();  
                    Rectangle bounds = gc.getBounds();
                    frame.setLocation((int) ((bounds.width / 2) - (frame.getSize().width / 2)),  
                                      (int) ((bounds.height / 2) - (frame.getSize().height / 2)));
                    frame.setVisible(true);
                }
            });
        } catch (RemoteException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
