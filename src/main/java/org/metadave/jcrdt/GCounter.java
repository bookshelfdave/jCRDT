package org.metadave.jcrdt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dparfitt on 7/9/15.
 */
public class GCounter {
    Map<String, Long> m;
    public GCounter() {
        m = new HashMap<String, Long>();
    }

    public void inc(String id) {
        inc(id, 1l);
    }

    public void inc(String id, long tosum) {
        //GCounter res = new GCounter();
        if(m.containsKey(id)) {
            m.put(id, m.get(id) + tosum);
        } else {
            m.put(id, tosum);
        }
    }

    public long read() {
        long acc = 0;
        for(long l : m.values()) {
            acc += l;
        }
        return acc;
    }

    public void join(GCounter o) {
        for(Map.Entry<String, Long> entry :o.m.entrySet()) {
            if(m.containsKey(entry.getKey())) {
                // update
                //System.out.println(entry.getKey() + ":" + m.get(entry.getKey()) + ", " + entry.getValue());
                long val = Math.max(m.get(entry.getKey()), entry.getValue());
                m.put(entry.getKey(), val);
            } else {
                m.put(entry.getKey(), entry.getValue());
            }
        }
    }
}
