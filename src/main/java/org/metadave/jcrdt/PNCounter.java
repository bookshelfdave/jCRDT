package org.metadave.jcrdt;

/**
 * Created by dparfitt on 7/9/15.
 */
public class PNCounter {
    GCounter p;
    GCounter n;

    public PNCounter() {
        p = new GCounter();
        n = new GCounter();
    }

    public void inc(String id) {
        inc(id, 1);
    }
    public void inc(String id, long tosum) {
        p.inc(id, tosum);
    }

    public void dec(String id) {
        dec(id, 1);
    }
    public void dec(String id, long tosum) {
        n.inc(id, tosum);
    }

    public long read() {
        return p.read() - n.read();
    }
    public void join(PNCounter o) {
        p.join(o.p);
        n.join(o.n);
    }
}
