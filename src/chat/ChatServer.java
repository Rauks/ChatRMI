/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.itf.IChatServer;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Karl
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer{
    
    
    /**
     * Launch a Chat server.
     * 
     * @param args args the command line arguments.
     */
    public static void main(String[] args){
        
    }
}
