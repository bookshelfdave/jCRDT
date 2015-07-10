package org.metadave.jcrdt;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by dparfitt on 7/10/15.
 */
public class LWWRegisterTest {

    @Test
    public void testLWWRegister() {
        LWWRegister<Integer, String> lwwr_a = new LWWRegister<Integer, String>();

        LWWRegister<Integer, String> lwwr_b = new LWWRegister<Integer, String>();

        /*
        LWWReg: (3,World)
        LWWReg: (2,a)
        World
         */

        lwwr_a.write(1, "Hello");
        lwwr_a.write(0, "My");
        lwwr_a.write(3, "World");
        lwwr_a.write(2, "a");
        assertEquals("World", lwwr_a.read());
    }
}
