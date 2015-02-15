package keyboard;

public class cKeyEvent extends cEvent {
    static final long serialVersionUID = 1L;
    private int type;
    private int key; // only set if type is key
    public static final int	VK_0 =	48;
    public static final int	VK_1 =	49;
    public static final int	VK_2 =	50;
    public static final int	VK_3 =	51;
    public static final int	VK_4 =	52;
    public static final int	VK_5 =	53;
    public static final int	VK_6 =	54;
    public static final int	VK_7 =	55;
    public static final int	VK_8 =	56;
    public static final int	VK_9 =	57;
    public static final int	VK_NUMPAD0 = 96;
    public static final int	VK_NUMPAD1 = 97;
    public static final int	VK_NUMPAD2 = 98;
    public static final int	VK_NUMPAD3 = 99;
    public static final int	VK_NUMPAD4 = 100;
    public static final int	VK_NUMPAD5 = 101;
    public static final int	VK_NUMPAD6 = 102;
    public static final int	VK_NUMPAD7 = 103;
    public static final int	VK_NUMPAD8 = 104;
    public static final int	VK_NUMPAD9 = 105;
    public static final int	VK_SPACE = 32;
    public static final int	VK_A = 65;
    public static final int	VK_B = 66;
    public static final int	VK_C = 67;
    public static final int	VK_D = 68;
    public static final int	VK_E = 69;
    public static final int	VK_F = 70;
    public static final int	VK_G = 71;
    public static final int	VK_H = 72;
    public static final int	VK_I = 73;
    public static final int	VK_J = 74;
    public static final int	VK_K = 75;
    public static final int	VK_L = 76;
    public static final int	VK_M = 77;
    public static final int	VK_N = 78;
    public static final int	VK_O = 79;
    public static final int	VK_P = 80;
    public static final int	VK_Q = 81;
    public static final int	VK_R = 82;
    public static final int	VK_S = 83;
    public static final int	VK_T = 84;
    public static final int	VK_U = 85;
    public static final int	VK_V = 86;
    public static final int	VK_W = 87;
    public static final int	VK_X = 88;
    public static final int	VK_Y = 89;
    public static final int	VK_Z = 90;
    public static final int	VK_ENTER = 10;
    public static final int	VK_DOWN = 40;
    public static final int	VK_UP = 38;
    public static final int	VK_LEFT = 37;
    public static final int	VK_RIGHT = 39;
    public static final int     VK_CONTROL = 17;
    public static final int     VK_BACK_SPACE = 8;
    public static final int     VK_SHIFT = 16;
    public static final int     VK_ALT = 18;
    public static final int     VK_TAB = 9;
    public static final int     VK_ESCAPE = 27;
    public static final int     VK_SEMICOLON = 186;
    public static final int     VK_CAPS_LOCK = 20;
    public static final int     VK_INSERT = 45;
    public static final int     VK_DELETE = 46;
    public static final int     VK_MULTIPLY = 106;
    public static final int     VK_ADD = 107;
    public static final int     VK_SUBTRACT = 109;
    public static final int     VK_DECIMAL_POINT = 110;
    public static final int     VK_DIVIDE = 111;
    public static final int     VK_F1 = 112;
    public static final int     VK_F2 = 113;
    public static final int     VK_F3 = 114;
    public static final int     VK_F4 = 115;
    public static final int     VK_F5 = 116;
    public static final int     VK_F6 = 117;
    public static final int     VK_F7 = 118;
    public static final int     VK_F8 = 119;
    public static final int     VK_F9 = 120;
    public static final int     VK_F10 = 121;
    public static final int     VK_F11 = 122;
    public static final int     VK_F12 = 123;
    public static final int     VK_NUM_LOCK = 144;
    public static final int     VK_SCROLL_LOCK = 145;
    public static final int     VK_EQUAL_SIGN = 187;
    public static final int     VK_COMMA = 188;
    public static final int     VK_DASH = 189;
    public static final int     VK_PERIOD = 190;
    public static final int     VK_FORWARD_SLASH = 191;
    public static final int     VK_OPEN_BRACKET = 219;
    public static final int     VK_BACK_SLASH = 220;
    public static final int     VK_CLOSE_BRACKET = 221;
    public static final int     VK_SINGLE_QUOTE = 222;
    
    
    
    
    
    
    public static final int	KEY_DOWN = 0;
    public static final int	KEY_UP = 1;
    public static final int	KEY_MOVE = 2;

    cKeyEvent ( int type, int c ) {
        this.key = c;
        this.type = type;
    }

    public void SetKey( int c ) {
        this.key = c;
    }

    public int GetKey() {
        return this.key;
    }

    public int GetType () {
        return this.type;
    }
    public Point GetPoint() {
        return null;
    }

}
