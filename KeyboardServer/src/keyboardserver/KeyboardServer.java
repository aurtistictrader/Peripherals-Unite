package keyboardserver;

import java.awt.AWTException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardServer {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static ObjectInputStream objInput;
    private static Robot robot;
    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(14444);  //Server socket
            robot = new Robot();
        } catch (IOException e) {
            System.out.println("Could not listen on port: 14444");
        } catch (AWTException ex) {
            Logger.getLogger(KeyboardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Server started. Listening to the port 14444");

        
        while (true) {
            try {
 
                clientSocket = serverSocket.accept();   //accept the client connection
                objInput = new ObjectInputStream(clientSocket.getInputStream());
                Event event = (Event) objInput.readObject();
                initiateAction(event);
                
                objInput.close();
                clientSocket.close();

            } catch (IOException ex) {
                System.out.println("Problem in message reading");
            } catch (ClassNotFoundException cx) {
                cx.printStackTrace();
            }
        }
    }
    
    private static void initiateAction(Event event) {
        int key = event.GetKey();
        switch (event.GetType()) {
            case keyboardserver.KeyEvent.KEY_DOWN:
                robot.keyPress(key);
                break;
            case keyboardserver.KeyEvent.KEY_UP:
                robot.keyRelease(key);
                break;
        }
    }
}