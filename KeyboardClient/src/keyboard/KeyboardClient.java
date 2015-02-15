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
import java.io.ObjectOutputStream;
import java.net.Socket;
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
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KeyboardClient().setVisible(true);
            }
        });
        try {
            
            clientSocket = new Socket("172.17.74.44", 14444);
            objOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            x = width / 2;
            y = height / 2;
        } catch (Exception e) {} ;
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
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
    }                                   

    private void formKeyPressed(java.awt.event.KeyEvent evt) { 
        try {
            if(evt.getID() == KeyEvent.KEY_PRESSED) {
                cKeyEvent socketEvent = new cKeyEvent(0,evt.getKeyCode());
                objOutput.writeObject(socketEvent);
                keepAlive();
//            objOutput.close();
//            clientSocket.close();
            } else if (evt.getID() == KeyEvent.KEY_RELEASED) {
//                cKeyEvent socketEvent = new cKeyEvent(1,evt.getKeyCode());
//                objOutput.writeObject(socketEvent);
            }
        } catch (Exception e) {} ;
    }                               

    private void formKeyReleased(java.awt.event.KeyEvent evt) {
        try {
            if(evt.getID() == KeyEvent.KEY_PRESSED) {
    //            cKeyEvent socketEvent = new cKeyEvent(0,evt.getKeyCode());
    //            objOutput.writeObject(socketEvent);
            } else if (evt.getID() == KeyEvent.KEY_RELEASED) {
                cKeyEvent socketEvent = new cKeyEvent(1,evt.getKeyCode());
                objOutput.writeObject(socketEvent);
                keepAlive();
//            objOutput.close();
//            clientSocket.close();
            }
        } catch (Exception e) {} ;
    }
   
    private void formMousePressed(java.awt.event.MouseEvent evt) {
        
    }
    private void formMouseReleased(java.awt.event.MouseEvent evt) {
//        try {
//            if (evt.getID() == )
//        }
    }
    private void formMouseMoved(java.awt.event.MouseEvent evt) {
        try {
            if (    evt.getX() > width - 200 || 
                    evt.getX() < 200 ||
                    evt.getY() > height - 100 ||
                    evt.getY() < 100 ){
                
                // send to server
                Point t = new Point(evt.getX() - x, evt.getY()- y);
                cMouseEvent socketEvent = new cMouseEvent(cMouseEvent.MOUSEMOVE,t);
                objOutput.writeObject(socketEvent);
                keepAlive();
                
                x = evt.getX();
                y = evt.getY();
                // reset to middle
                Robot robot = new Robot();
                robot.mouseMove(width / 2, height / 2);
                
            }
        } catch (Exception e) {
            
        };
    }
    private void keepAlive() {
        try {
            clientSocket = new Socket("172.17.74.44", 14444);
            objOutput = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

