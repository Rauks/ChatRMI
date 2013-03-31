/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.itf.IChatClient;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Karl
 */
public class ChatClient extends UnicastRemoteObject implements IChatClient{
    
    
    /**
     * Launch a Chat client.
     * 
     * @param args args the command line arguments.
     */
    public static void main(String[] args){
        
    }
}
