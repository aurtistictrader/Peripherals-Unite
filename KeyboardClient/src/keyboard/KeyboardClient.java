/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keyboard;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.SwingUtilities;
/**
 *
 * @author chengpeng123
 */
public class KeyboardClient extends javax.swing.JFrame {

    KeyboardClient() {
        initComponents();
    }
    /**
     * @param args the command line arguments
     */
    private static Socket clientSocket;
    private static ObjectOutputStream objOutput;
    private static int x,y;
    private static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int)size.getWidth();
    private static int height = (int) size.getHeight();
    private static String ipaddress = "172.17.74.44";
    public static String ip_address;
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter host's IP address: ");
            ip_address = in.nextLine();
            clientSocket = new Socket(ip_address, 14444);
            objOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            x = width / 2;
            y = height / 2;
        } catch (Exception e) {} ;
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KeyboardClient().setVisible(true);
            }
        });
    }
     private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                formMouseWheel(e);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold> 
     
    private void formComponentShown(java.awt.event.ComponentEvent evt) {
        this.setSize(width, height);
    }                                   

    private void formKeyPressed(java.awt.event.KeyEvent evt) { 
        try {
            if(evt.getID() == KeyEvent.KEY_PRESSED) {
                cKeyEvent socketEvent = new cKeyEvent(0,evt.getKeyCode());
                objOutput.writeObject(socketEvent);
                keepAlive();
            } 
        } catch (Exception e) {} ;
    }                               

    private void formKeyReleased(java.awt.event.KeyEvent evt) {
        try {
            if (evt.getID() == KeyEvent.KEY_RELEASED) {
                cKeyEvent socketEvent = new cKeyEvent(1,evt.getKeyCode());
                objOutput.writeObject(socketEvent);
                keepAlive();
            }
        } catch (Exception e) {} ;
    }
   
    private void formMousePressed(java.awt.event.MouseEvent evt) {
        try {
            Point t = new Point();
            cMouseEvent socketEvent;
            if (SwingUtilities.isLeftMouseButton(evt)) {
                socketEvent = new cMouseEvent(cMouseEvent.MOUSEDOWN,t);
                socketEvent.mkey = 0;
                objOutput.writeObject(socketEvent);
                keepAlive();
            } else if (SwingUtilities.isRightMouseButton(evt)) {
                socketEvent = new cMouseEvent(cMouseEvent.MOUSEDOWN,t);
                socketEvent.mkey = 1;
                objOutput.writeObject(socketEvent);
                keepAlive();
            } else if (SwingUtilities.isMiddleMouseButton(evt)) {
                socketEvent = new cMouseEvent(cMouseEvent.MOUSEDOWN,t);
                socketEvent.mkey = 2;
                objOutput.writeObject(socketEvent);
                keepAlive();
            }
            
        } catch (Exception e) {
            
        }
    }
    private void formMouseReleased(java.awt.event.MouseEvent evt) {
        try {
            Point t = new Point();
            cMouseEvent socketEvent;
            if (SwingUtilities.isLeftMouseButton(evt)) {
                socketEvent = new cMouseEvent(cMouseEvent.MOUSEUP,t);
                socketEvent.mkey = 0;
                objOutput.writeObject(socketEvent);
                keepAlive();
            } else if (SwingUtilities.isRightMouseButton(evt)) {
                socketEvent = new cMouseEvent(cMouseEvent.MOUSEUP,t);
                socketEvent.mkey = 1;
                objOutput.writeObject(socketEvent);
                keepAlive();
            } else if (SwingUtilities.isMiddleMouseButton(evt)) {
                socketEvent = new cMouseEvent(cMouseEvent.MOUSEUP,t);
                socketEvent.mkey = 2;
                objOutput.writeObject(socketEvent);
                keepAlive();
            }
        } catch (Exception e) {
            
        }
    }
    private void formMouseMoved(java.awt.event.MouseEvent evt) {
        try {
                // send to server
                Point t = new Point(evt.getX() - x, evt.getY()- y);
                cMouseEvent socketEvent = new cMouseEvent(cMouseEvent.MOUSEMOVE,t);
                objOutput.writeObject(socketEvent);
                keepAlive();
                
                x = evt.getX();
                y = evt.getY();
                if (    evt.getX() > width/2 || 
                        evt.getX() < width/2 ||
                        evt.getY() > height/2 ||
                        evt.getY() < height/2 ){
                    // reset to middle
                    Robot robot = new Robot();
                    robot.mouseMove(width / 2, height / 2);
                    Point t2= new Point(evt.getX() - width / 2, evt.getY() - height/2); 
                    cMouseEvent socketEvent2 = new cMouseEvent(cMouseEvent.MOUSEMOVE,t2);
                    objOutput.writeObject(socketEvent2);
                    keepAlive();
                    
                }
        } catch (Exception e) {
            
        };
    }
    private void formMouseDragged(java.awt.event.MouseEvent evt) {
        try {
            formMouseMoved(evt);
        } catch (Exception e) {
        
        }
    }
    private void formMouseWheel(java.awt.event.MouseWheelEvent evt) {
        try {
            int notches = evt.getWheelRotation();
            cMouseWheelEvent socketEvent = new cMouseWheelEvent(cMouseWheelEvent.SCROLL, notches);
            objOutput.writeObject(socketEvent);
            keepAlive();
        } catch (Exception e) {
            
        }
    }
    private void keepAlive() {
        try {
            clientSocket = new Socket(ip_address, 14444);
            objOutput = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

