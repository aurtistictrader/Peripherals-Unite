package keyboardclient;

import java.io.Serializable;
/**
 * Created by Michael on 2/14/2014.
 */
abstract class cEvent implements Serializable {
        abstract int GetKey();
        abstract int GetType();
        
}
