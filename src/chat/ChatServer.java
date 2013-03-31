/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.itf.IChatClient;
import chat.itf.IChatServer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
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
public class ChatServer extends UnicastRemoteObject implements IChatServer{
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
        for(Iterator<IChatClient> it = this.clients.iterator(); it.hasNext();){
            IChatClient c = it.next();
            try{
                c.receive(msg);
            } catch(RemoteException ex){
                //Client disconnected
                this.clients.remove(c);
            }
        }
    }
    
    /**
     * Register a chat server into the RMIregistry.
     * 
     * @param args args the command line arguments.
     */
    public static void main(String[] args){
        try {
            Logger.getLogger(ChatServer.class.getName()).log(Level.INFO, "Server start.");
            String name = "chat";
            Remote remote = (IChatServer) new ChatServer();
            
            //Create in server registry. (replace rmiregistry separated process)
            LocateRegistry.createRegistry(IChatServer.DEFAULT_PORT).bind(name, remote);
            Logger.getLogger(ChatServer.class.getName()).log(Level.INFO, "Registered: {0} -> {1}[{2}]", new Object[]{name, remote.getClass().getName(), remote});
            
            //Keep the server running
            try {
                System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Logger.getLogger(ChatServer.class.getName()).log(Level.INFO, "Server stopped.");
            
        } catch (AlreadyBoundException | RemoteException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
