/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.itf.IChatClient;
import chat.itf.IChatServer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Karl
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer{
    public ChatServer() throws RemoteException{
        
    }

    @Override
    public void register(IChatClient client) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void disparch(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Launch a Chat server.
     * 
     * @param args args the command line arguments.
     */
    public static void main(String[] args){
        
    }
}
