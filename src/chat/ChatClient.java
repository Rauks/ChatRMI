/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.itf.IChatClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Karl
 */
public class ChatClient extends UnicastRemoteObject implements IChatClient{
    public ChatClient() throws RemoteException{
        
    }
    
    @Override
    public void connect(String host) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void send(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void receive(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Launch a Chat client.
     * 
     * @param args args the command line arguments.
     */
    public static void main(String[] args){
        
    }

}
