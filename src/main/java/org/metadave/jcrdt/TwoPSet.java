package org.metadave.jcrdt;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dparfitt on 7/9/15.
 */
public class TwoPSet<T> {
    private Set<T> ss;
    private Set<T> st; // tombstones

    public TwoPSet() {
        ss = new HashSet<T>();
        st = new HashSet<T>();
    }

    public void add(T t)
    {
        if(st.contains(t)) {
            return;
        }
        ss.add(t);
    }

    public boolean contains(T t) {
        return ss.contains(t) && !st.contains(t);
    }

    public void remove(T t) {
        if(ss.contains(t)) {
            ss.remove(t);
            st.add(t);
        }
    }

    public void join(TwoPSet<T> other) {
        for(T tombstone : other.st) {
            st.add(tombstone);
            ss.remove(tombstone);
        }
        for(T object : other.ss) {
            ss.add(object);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GSet) {
            return this.ss == ((TwoPSet) obj).ss &&
                    this.st == ((TwoPSet) obj).st;
        } else {
            return false;
        }
    }
}
