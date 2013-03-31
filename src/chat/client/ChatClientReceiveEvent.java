/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

/**
 *
 * @author Karl
 */
public class ChatClientReceiveEvent {
    private String message;
    
    /**
     * Create a ChatClientReceiveEvent and associate a message.
     * 
     * @param message 
     */
    public ChatClientReceiveEvent(String message){
        this.message = message;
    }
    
    /**
     * Return the received message associed to the event.
     * 
     * @return The received message;
     */
    public String getMessage(){
        return this.message;
    }
}
