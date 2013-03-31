/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.itf.IChatClient;
import chat.itf.IChatServer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

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
    public void register(IChatClient client) {
        this.clients.add(client);
    }

    @Override
    public void disparch(String msg) {
        for(Iterator<IChatClient> it = this.clients.iterator(); it.hasNext();){
            it.next().receive(msg);
        }
    }
    
    /**
     * Launch a Chat server.
     * 
     * @param args args the command line arguments.
     */
    public static void main(String[] args){
        
    }
}
