/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import chat.itf.IChatClient;
import chat.itf.IChatServer;
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
            ChatClient client = new ChatClient();
            client.connect("localhost");
            
            System.out.println("START");
            client.send("Hello server !");
            client.send("This is chat !");
            client.send("Goodbye !");
            for(Iterator<String> it = client.received.iterator(); it.hasNext();){
                System.out.println(it.next());
            }
            System.out.println("WAIT");
            
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(Iterator<String> it = client.received.iterator(); it.hasNext();){
                System.out.println(it.next());
            }
            System.out.println("END");
            
        } catch (RemoteException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
