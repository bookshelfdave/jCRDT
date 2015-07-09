package org.metadave.jcrdt;

import org.junit.Test;

/**
 * Created by dparfitt on 7/9/15.
 */
public class GCounterTest {
    @Test
    public void testGCounter() {
        /*
        GCounter: ( idx->5 idy->2 )
        GCounter: ( idx->5 idy->2 )
         */

        GCounter gc1 = new GCounter();

        GCounter gc2 = new GCounter();
        gc1.inc("foo");
        gc1.inc("foo");
        gc1.inc("foo");
        gc1.inc("bar");

        gc2.inc("bar");
        gc2.inc("baz");
        gc2.inc("baz");

        System.out.println(gc1.read());
        System.out.println(gc2.read());

        gc1.join(gc2);
        System.out.println(gc1.read());
    }
}
