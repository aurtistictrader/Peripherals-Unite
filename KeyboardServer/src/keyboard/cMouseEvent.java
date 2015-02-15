package keyboard;

public class cMouseEvent extends cEvent {
    static final long serialVersionUID = 1L;
    public final static int MOUSEDOWN = 3;
    public final static int MOUSEUP = 4;
    public final static int MOUSEMOVE = 5;
    
    public int mkey;
    private int type;
    private Point point; /* only set for mouse type
                    position of the cursor */
    cMouseEvent() {
        type = 1;
        point = new Point(0, 0);
    }

    cMouseEvent ( int type, Point point ) {
        this.type = type;
        this.point = point;
    }
    public int GetKey() {
        return this.mkey;
    }
    
    public int GetType() {
        return this.type;
    }
    
    public Point GetPoint() {
        return point;
    }

}
