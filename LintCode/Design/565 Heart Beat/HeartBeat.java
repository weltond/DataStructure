/**
* This reference program is provided by @jiuzhang.com
* Copyright is reserved. Please indicate the source for forwarding
*/

public class HeartBeat {
	
	// String: ip address
	// Integer: last pinged timestamp
    public Map<String, Integer> slaves_ip_map;
    public int k;

    public HeartBeat() {
        // initialize your data structure here
        this.slaves_ip_map = new HashMap<String, Integer>();
    }

    // @param slaves_ip_list a list of slaves'ip addresses
    // @param k an integer
    // @return void
    public void initialize(List<String> slaves_ip_list, int k) {
        // Write your code here
        this.k = k;
		
		// Initialize each ip's ping timestamp
        for (String ip : slaves_ip_list)
            this.slaves_ip_map.put(ip, 0);
    }

    // @param timestamp current timestamp in seconds
    // @param slave_ip the ip address of the slave server
    // @return nothing
    public void ping(int timestamp, String slave_ip) {
        // Write your code here
        if (!this.slaves_ip_map.containsKey(slave_ip))
            return;
        
        this.slaves_ip_map.put(slave_ip, timestamp);
    }

    // @param timestamp current timestamp in seconds
    // @return a list of slaves'ip addresses that died
    public List<String> getDiedSlaves(int timestamp) {
        // Write your code here
        List<String> results = new ArrayList<String>();
        
        Iterator iter = this.slaves_ip_map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            String ip = (String) entry.getKey();
			// time is last pinged timestamp
            int time = (Integer) entry.getValue();
            if (time <= timestamp - 2 * k)
                results.add(ip);
        }
        return results;
    }
}