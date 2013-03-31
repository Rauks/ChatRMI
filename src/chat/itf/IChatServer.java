/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.itf;

import java.rmi.Remote;

/**
 *
 * @author Karl
 */
public interface IChatServer extends Remote{
    /**
     * Register a client to the server's client list.
     * 
     * @param client The client to register.
     */
    public void register(IChatClient client);
    
    /**
     * Send a message to all the server's clients.
     * 
     * @param msg The message to send.
     */
    public void disparch(String msg);
}
