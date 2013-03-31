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
public interface IChatClient extends Remote{
    /**
     * Connect the client to a server designed by his host.
     * 
     * @param host The chat server's host.
     */
    public void connect(String host);
    
    /**
     * Send a message to the server.
     * 
     * @param msg The message to send.
     */
    public void send(String msg);
    
    /**
     * Reveive a message from the server.
     * 
     * @param msg The message to receive.
     */
    public void receive(String msg);
}
