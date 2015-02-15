/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keyboardclient;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.io.ObjectOutputStream;
import java.net.Socket;
import keyboardclient.cEvent;
import keyboardclient.cKeyEvent;
/**
 *
 * @author chengpeng123
 */
public class KeyboardClient {

    /**
     * @param args the command line arguments
     */
    private static Socket clientSocket;
    private static ObjectOutputStream objOutput;
    public static void main(String[] args) {
        
        try {
            clientSocket = new Socket("172.17.75.255", 14444);
            objOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            
//            objOutput.close();
//            clientSocket.close();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    AWTEventListener listener = new AWTEventListener() {
    @Override
    public void eventDispatched(AWTEvent event) {
      try {
        KeyEvent evt = (KeyEvent)event;
        if(evt.getID() == KeyEvent.KEY_PRESSED) {
            cKeyEvent socketEvent = new cKeyEvent(0,evt.getKeyCode());
            objOutput.writeObject(socketEvent);
        } else if (evt.getID() == KeyEvent.KEY_RELEASED) {
            cKeyEvent socketEvent = new cKeyEvent(1,evt.getKeyCode());
            objOutput.writeObject(socketEvent);
        }
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  };
}
