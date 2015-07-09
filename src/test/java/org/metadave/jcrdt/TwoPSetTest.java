package org.metadave.jcrdt;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by dparfitt on 7/9/15.
 */
public class TwoPSetTest {
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
}
