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
public interface IChatClient extends Remote{
    /**
     * Connect the client to a server designed by his host.
     * 
     * @param host The chat server's host.
     * @param name Server name in RMIregistry.
     */
    public void connect(String host, String name) throws RemoteException;
    
    /**
     * Send a message to the server.
     * 
     * @param msg The message to send.
     */
    public void send(String msg) throws RemoteException;
    
    /**
     * Reveive a message from the server.
     * 
     * @param msg The message to receive.
     */
    public void receive(String msg) throws RemoteException;
}
