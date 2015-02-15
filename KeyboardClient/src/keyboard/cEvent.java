package keyboard;

import java.io.Serializable;

abstract class cEvent implements Serializable {
        abstract int GetKey();
        abstract int GetType();
        
}
