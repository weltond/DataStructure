/**
 * Definition of Location:
 * class Location {
 *     public double latitude, longitude;
 *     public static Location create(double lati, double longi) {
 *         // This will create a new location object
 *     }
 * };
 * Definition of Restaurant
 * class Restaurant {
 *     public int id;
 *     public String name;
 *     public Location location;
 *     public static Restaurant create(String name, Location location) {
 *         // This will create a new restaurant object,
 *         // and auto fill id
 *     }
 * };
 * Definition of Helper
 * class Helper {
 *     public static get_distance(Location location1, Location location2) {
 *         // return distance between location1 and location2.
 *     }
 * };
 * class GeoHash {
 *     public static String encode(Location location) {
 *         // return convert location to a GeoHash string
 *     }
 *     public static Location decode(String hashcode) {
 *          // return convert a GeoHash string to location
 *     }
 * };
 */
 
 class Node {
	public double distance;
	public Restaurant restaurant;
	public Node(double d, Restaurant r) {
		distance = d;
		restaurant = r;
	}
 }
 
 public class MiniYelp {
	public NavigableMap<String, Restaurant> restaurants;
	public Map<Integer, String> ids;
	public MiniYelp() {
		ids = new HashMap<>();
		restaurants = new TreeMap<>();
	}
	
	// @param name a string
    // @param location a Location
    // @return an integer, restaurant's id
    public int addRestaurant(String name, Location location) {
        Restaurant restaurant = Restaurant.create(name, location);
		String hashcode = GeoHash.encode(location) + "." + restaurant.id;	// id is auto filled by Restaurant class's create() function
		
		System.out.println("Add hashcode for " + name + " is " + hashcode);
		
		ids.put(restaurant.id, hashcode);
		restaurants.put(hashcode, restaurant);
		return restaurant.id;
    }

    // @param restaurant_id an integer
    public void removeRestaurant(int restaurant_id) {
        if (ids.containsKey(restaurant_id)) {
			String hashcode = ids.get(restaurant_id);
			ids.remove(restaurant_id);
			
			if (restaurants.containsKey(hashcode)) {
				restaurants.remove(hashcode);
			}
		}
    }

	// Use geohash to do coarse filter
	//		1. Pick geohash resolution slightly larger than required distance k
	//		2. Use subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) in NavigableMap class to do query range based on geohash
	
    // @param location a Location
    // @param k an integer, distance smaller than k miles
    // @return a list of restaurant's name and sort by 
    // distance from near to far.
    public List<String> neighbors(Location location, double k) {
        int len = getLength(k);
		String hashcode = GeoHash.encode(location);
		
		System.out.println("Hash for : (" + location.latitude + "," + location.longitude + ") is " + hashcode);   
		
		hashcode = hashcode.substring(0, len);
		
		List<Node> result = new ArrayList<>();
		
		for (Map.Entry<String, Restaurant> entry : restaurants.subMap(hashcode, true, hashcode + "{", true).entrySet()) {
			String key = entry.getKey();
			Restaurant value = entry.getValue();
			double distance = Helper.get_distance(location, value.location);
			
			System.out.println("Distance from " + value.name + " is " + distance);
			
			if (distance <= k) {
				result.add(new Node(distance, value));
			}
		}
		
		Collections.sort(result, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				// CANNOT simply substract two double here!!!
				if (n1.distance < n2.distance) return -1;
				else if (n1.distance > n2.distance) return 1;
				else return 0;
			}
		});
		
		List<String> rt = new ArrayList();
		int n = result.size();
		
		for (int i = 0; i < n; ++i) {
			rt.add(result.get(i).restaurant.name);
		}
		
		return rt;
    }
	
	int getLength(double k) {
		double[] ERROR = {2500, 630, 78, 20, 2.4, 0.61, 0.076, 0.01911, 0.00478, 0.0005971, 0.0001492, 0.0000186};
		for (int i = 0; i < 12; ++i) {
			if (k > ERROR[i]) 
				return i;
		}
		return 12;
	}
 }
 
 /** 
 Input
		addRestaurant("Lint Cafe", 10.4999999, 11.599999)
		addRestaurant("Code Cafe", 10.4999999, 11.512109)
		neighbors(10.5, 11.6, 6.7)
		removeRestaurant(1)
		neighbors(10.5, 9.6, 6.7)
  
  Your stdout
		Add hashcode for Lint Cafe is s3b5xzqygtf5.1
		Add hashcode for Code Cafe is s3b5tzqygtfe.2
		Hash for : (10.5,11.6) is s3b5xzqygtfg
		Distance from Code Cafe is 9.612097863060802
		Distance from Lint Cafe is 1.0992799483315577E-4
		Hash for : (10.5,9.6) is s1ygdrqwbjyg
  
  Output
		1
		2
		["Lint Cafe"]
		[]
 */