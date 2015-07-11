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


        lwwr_a.write(1, "Hello");
        lwwr_a.write(0, "My");
        lwwr_a.write(3, "World");
        lwwr_a.write(2, "a");
        lwwr_b.write(0, "X");

        assertEquals("World", lwwr_a.read());
        assertEquals("X", lwwr_b.read());

        lwwr_a.join(lwwr_b);
        assertEquals("World", lwwr_a.read());

        lwwr_b.write(9, "Foo");
        lwwr_a.join(lwwr_b);
        assertEquals("Foo", lwwr_a.read());
    }
}
