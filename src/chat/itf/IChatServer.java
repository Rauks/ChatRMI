/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.itf;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Karl
 */
public interface IChatServer extends Remote{
    public static final int DEFAULT_PORT = 1099;
    
    /**
     * Register a client to the server's client list.
     * 
     * @param client The client to register.
     */
    public void register(IChatClient client) throws RemoteException;
    
    /**
     * Send a message to all the server's clients.
     * 
     * @param msg The message to send.
     */
    public void disparch(String msg) throws RemoteException;
}
