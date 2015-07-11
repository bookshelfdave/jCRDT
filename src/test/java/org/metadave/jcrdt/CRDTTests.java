package org.metadave.jcrdt;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by dparfitt on 7/9/15.
 */
public class CRDTTests {
    @Test
    public void TwoPSetTest() {
        TwoPSet<Integer> psa = new TwoPSet<Integer>();
        psa.add(1);
        psa.add(2);
        psa.add(3);
        psa.remove(2);
        psa.add(2);
        assertTrue(psa.contains(1));
        assertFalse(psa.contains(2));
        assertTrue(psa.contains(3));

        TwoPSet<Integer> psb = new TwoPSet<Integer>();
        psb.add(10);
        psb.remove(100);
        psb.add(11);
        psb.add(12);

        psb.remove(12);
        psb.add(12);

        assertTrue(psb.contains(10));
        assertTrue(psb.contains(11));
        assertTrue(!psb.contains(12));

        psa.join(psb);
        assertTrue(psa.contains(1));
        assertTrue(psa.contains(3));
        assertTrue(psa.contains(10));
        assertTrue(psa.contains(11));
        psa.add(2); // tombstone prevents addition
        assertFalse(psa.contains(2));
        psa.add(12); // tombstone prevents addition
        assertFalse(psa.contains(12));
    }

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


    @Test
    public void testGSet() {
        GSet<Integer> s1 = new GSet<Integer>();
        s1.add(1);
        s1.add(2);
        assertTrue(s1.contains(1));
        assertTrue(s1.contains(2));

        GSet<Integer> s2 = new GSet<Integer>();
        s2.add(1);
        s2.add(3);
        assertTrue(s2.contains(1));
        assertTrue(s2.contains(3));

        s1.join(s2);
        assertTrue(s1.contains(1));
        assertTrue(s1.contains(2));
        assertTrue(s1.contains(3));
    }

    @Test
    public void testPNCounter() {
        PNCounter pn1 = new PNCounter();
        pn1.inc("foo");
        pn1.inc("foo");
        pn1.inc("foo");

        pn1.dec("foo");
        pn1.dec("bar");

        System.out.println(pn1.read());
        PNCounter pn2 = new PNCounter();

        pn2.inc("foo");
        pn2.inc("foo");

        pn2.dec("foo");
        pn2.inc("bar");
        pn2.inc("bar");
        System.out.println(pn2.read());

    }

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
