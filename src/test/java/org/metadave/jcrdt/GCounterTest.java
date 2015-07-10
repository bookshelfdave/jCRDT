package org.metadave.jcrdt;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by dparfitt on 7/9/15.
 */
public class GCounterTest {
    @Test
    public void testGCounter() {
        GCounter gc1 = new GCounter();
        GCounter gc2 = new GCounter();

        gc1.inc("foo");
        gc1.inc("foo");
        gc1.inc("foo");
        gc1.inc("bar");
        gc1.inc("bar");

        gc2.inc("bar");
        gc2.inc("baz");
        gc2.inc("baz");

        assertEquals(5, gc1.read());
        assertEquals(3, gc2.read());

        gc1.join(gc2);
        assertEquals(7, gc1.read()); // max of 2 vs 1 in gc1 and gc2 = 2
    }

}
