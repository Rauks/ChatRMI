/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import java.io.Serializable;
import javax.swing.JTextArea;

/**
 *
 * @author Karl
 */
public class ChatFlow extends JTextArea implements Serializable, ChatClientReceiveListener {
    private boolean autoTextScroll;    
    
    public ChatFlow() {
        this.autoTextScroll = false;
    }

    public boolean isAutoTextScroll() {
        return autoTextScroll;
    }

    public void setAutoTextScroll(boolean autoScroll) {
        this.autoTextScroll = autoScroll;
    }
    
    /**
     * Append the event's message to the end of the flow.
     * Parent vertical scrollbar is scrolled to the bottom only if it is already at the bottom before the event reception.
     * 
     * @param evt The received event. 
     */
    @Override
    public void receive(ChatClientReceiveEvent evt) {
        this.append("\n");
        this.append(evt.getMessage());
        
        if(this.autoTextScroll){
            this.setCaretPosition(this.getText().length());
        }
    }
}
