package org.metadave.jcrdt;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by dparfitt on 7/9/15.
 */
public class GSetTest {
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
}
