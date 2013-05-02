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

    /**
     * Return the current value of the auto-scroll option.
     * 
     * @return <code>true</code> if the auto-scroll is activated.
     */
    public boolean isAutoTextScroll() {
        return autoTextScroll;
    }

    /**
     * If the auto-scroll is set to <code>true</code> the textarea will scroll automatically at the bottom when a new text is added. 
     * 
     * @param autoScroll Active or desactive the auto-scroll
     */
    public void setAutoTextScroll(boolean autoScroll) {
        this.autoTextScroll = autoScroll;
    }
    
    /**
     * Append the event's message to the end of the flow.
     * <p>
     * If the auto-scroll is set to <code>true</code> the textarea will scroll automatically at the bottom when a new text is added. 
     * 
     * @param evt The received event.
     * @see ChatFlow#setAutoTextScroll
     */
    @Override
    public void chatClientReceivePerformed(ChatClientReceiveEvent evt) {
        this.append("\n");
        this.append(evt.getMessage());
        
        if(this.autoTextScroll){
            this.setCaretPosition(this.getText().length());
        }
    }
}
