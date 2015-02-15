package keyboard;

import java.awt.AWTException;
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardServer {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static ObjectInputStream objInput;
    private static Robot robot;
    private static int x,y;
    private static Dimension size;
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

        size = Toolkit.getDefaultToolkit().getScreenSize();
        x = (int) size.getWidth() / 2;
        y = (int) size.getHeight() / 2;
        robot.mouseMove(x, y);
        
        while (true) {
            try {
 
                clientSocket = serverSocket.accept();   //accept the client connection
                objInput = new ObjectInputStream(clientSocket.getInputStream());
                cEvent event = (cEvent) objInput.readObject();
                System.out.println(event.GetKey());
                initiateAction(event);
                
                objInput.close();
                clientSocket.close();

            } catch (IOException ex) {
                System.out.println("Problem in message reading");
                System.out.println(ex.toString());
            } catch (ClassNotFoundException cx) {
                cx.printStackTrace();
                System.out.println(cx.toString());
            }
        }
    }
    
    private static void initiateAction(cEvent event) {
        int key = event.GetKey();
        switch (event.GetType()) {
            case keyboard.cKeyEvent.KEY_DOWN:
                robot.keyPress(key);
                break;
            case keyboard.cKeyEvent.KEY_UP:
                robot.keyRelease(key);
                break;
            case keyboard.cMouseEvent.MOUSEMOVE:
                Point p = event.GetPoint();
                robot.mouseMove(x + p.getX(), y + p.getY());
                x = x + p.getX();
                y = y + p.getY();
                break;
            case keyboard.cMouseEvent.MOUSEDOWN:
                switch (key) {
                    case 0:
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        break;
                    case 1:
                        robot.mousePress(InputEvent.BUTTON3_MASK);
                        break;
                    case 2:
                        robot.mousePress(InputEvent.BUTTON2_MASK);
                        break;
                }
                break;
            case keyboard.cMouseEvent.MOUSEUP:
                switch (key) {
                    case 0:
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        break;
                    case 1:
                        robot.mouseRelease(InputEvent.BUTTON3_MASK);
                        break;
                    case 2:
                        robot.mouseRelease(InputEvent.BUTTON2_MASK);
                        break;
                }
                break;
            case keyboard.cMouseWheelEvent.SCROLL:
                robot.mouseWheel(key);
                break;
        }
    }
}