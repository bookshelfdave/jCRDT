package org.metadave.jcrdt;

/**
 * Created by dparfitt on 7/10/15.
 */
public class LWWRegister<U extends Comparable, V> {
    U u;
    V v;

    public void write(U up, V vp) {
        join(up, vp);
    }

    public void join(U up, V vp) {
        if(u == null) {
            u = up;
            v = vp;
        } else if(up.compareTo(u) > 0) {
            u = up;
            v = vp;
        }
    }
    public void join(LWWRegister<U, V> other) {
        if(u == null) {
            u = other.u;
            v = other.v;
        } else if(other.u.compareTo(u) > 0) {
            u = other.u;
            v = other.v;
        }
    }

    public V read() {
        return v;
    }

    @Override
    public String toString() {
        return "LWWReg: [" + u.toString() + ", " + v.toString() + "]";
    }
}
