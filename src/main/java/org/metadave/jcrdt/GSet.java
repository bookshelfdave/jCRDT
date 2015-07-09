package org.metadave.jcrdt;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dparfitt on 7/9/15.
 */
public class GSet<T> {
    private Set<T> data;

    public GSet() {
        data = new HashSet<T>();
    }

    public void add(T t) {
        data.add(t);
    }

    public boolean contains(T elem) {
        return(data.contains(elem));
    }

    public void join(GSet<T> other) {
        data.addAll(other.data);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GSet) {
            return this.data == ((GSet) obj).data;
        } else {
            return false;
        }
    }
}
