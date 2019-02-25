class Resource {
    public int value;
    public int expired; // -1 means never expired until OutOfMemoryError
    
    public Resource(int value, int expired) {
        this.value = value;
        this.expired = expired;
    }
    
}

public class Memcache {
    // Integer: key
    // Resource: a class that stores value & ttl
    Map<Integer, Resource> client = null;
    
    public Memcache() {
        // do intialization if necessary
        client = new HashMap<Integer, Resource>();
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: An integer
     */
    public int get(int curtTime, int key) {
        if (!this.client.containsKey(key)) {
            return Integer.MAX_VALUE;
        }
        
        Resource res = this.client.get(key);
        
        if (res.expired >= curtTime || res.expired == -1) {
            System.out.println("IN: " + key + ", " + res.expired + " -> " + curtTime);
            return res.value;
        } else {
            System.out.println("EX: " + key + ", " + res.value + " -> " + curtTime);
            return Integer.MAX_VALUE;
        }
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param value: An integer
     * @param ttl: An integer
     * @return: nothing
     */
    public void set(int curtTime, int key, int value, int ttl) {
        int expired;
        if (ttl == 0) {
            expired = -1;
        } else {
            expired = curtTime + ttl - 1;
        }
        this.client.put(key, new Resource(value, expired));
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: nothing
     */
    public void delete(int curtTime, int key) {
        if (!this.client.containsKey(key)) {
            return;
        }
        
        this.client.remove(key);
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int incr(int curtTime, int key, int delta) {
        if (get(curtTime, key) == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        
        this.client.get(key).value += delta;
        
        return this.client.get(key).value;
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int decr(int curtTime, int key, int delta) {
        if (get(curtTime, key) == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        
        this.client.get(key).value -= delta;
        
        return this.client.get(key).value;
    }
}