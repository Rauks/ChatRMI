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
    public ChatFlow() {
    }

    @Override
    public void receive(ChatClientReceiveEvent evt) {
        this.append("\n");
        this.append(evt.getMessage());
    }
}
