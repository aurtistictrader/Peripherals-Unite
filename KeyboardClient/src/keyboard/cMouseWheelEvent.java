/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keyboard;

/**
 *
 * @author chengpeng123
 */
public class cMouseWheelEvent extends cEvent {
    static final long serialVersionUID = 1L;
    public final static int SCROLL = 6;
    
    private int type;
    private int key;
    cMouseWheelEvent() {
        type = 1;
    }

    cMouseWheelEvent ( int type, int key ) {
        this.type = type;
        this.key = key;
    }
    public int GetKey() {
        return this.key;
    }
    
    public int GetType() {
        return this.type;
    }
    
    public Point GetPoint() { return null; } ;
    
}
