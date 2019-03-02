## Description

Support two basic uber features:

1. Drivers report their locations.
2. Rider request a uber, return a matched driver.

When rider request a uber, match a closest available driver with him, then mark the driver not available.

When next time this matched driver report his location, return with the rider's information.

You can implement it with the following instructions:

- `report(driver_id, lat, lng)`
  - return null if no matched rider.
  - return matched trip information.

- `request(rider_id, lat, lng)`
  - create a trip with rider's information.
  - find a closest driver, mark this driver not available.
  - fill driver_id into this trip.
  - return trip
  
This is the definition of Trip in Java:

```java
public class Trip {
    public int id; // trip's id, primary key
    public int driver_id, rider_id; // foreign key
    public double lat, lng; // pick up location
}
```

## Example

Example 1:
```
Input:
  report(1, 36.1344, 77.5672) 
  report(2, 45.1344, 76.5672) 
  request(2, 39.1344, 76.5672) 
  report(1, 38.1344, 75.5672) 
  report(2, 45.1344, 76.5672) 
Output:
  null
  null
  Trip(rider_id: 2, driver_id: 1, lat: 39.1344, lng: 76.5672)
  Trip(rider_id: 2, driver_id: 1, lat: 39.1344, lng: 76.5672)
  null
```

Example 2:
```
Input:
  report(1, 36.1344, 77.5672)
  report(2, 45.1344, 76.5672)
  request(2, 39.1344, 76.5672)
  request(3, 78.1344, 134.5672)
Output:
  null
  null
  LOG(INFO): Trip(rider_id: 2, driver_id: 1, lat: 39.1344, lng: 76.5672)
  LOG(INFO): Trip(rider_id: 3, driver_id: 2, lat: 78.1344, lng: 134.5672)
```

## TO DO
```java
/**
 * Definition of Trip:
 * public class Trip {
 *     public int id; // trip's id, primary key
 *     public int driver_id, rider_id; // foreign key
 *     public double lat, lng; // pick up location
 *     public Trip(int rider_id, double lat, double lng);
 * }
 * Definition of Helper
 * class Helper {
 *     public static double get_distance(double lat1, double lng1,
                                         double lat2, double lng2) {
 *         // return distance between (lat1, lng1) and (lat2, lng2)
 *     }
 * };
 */
public class MiniUber {

    public MiniUber() {
        // initialize your data structure here.
    }

    // @param driver_id an integer
    // @param lat, lng driver's location
    // return matched trip information if there have matched rider or null
    public Trip report(int driver_id, double lat, double lng) {
        // Write your code here
    }

    // @param rider_id an integer
    // @param lat, lng rider's location
    // return a trip
    public Trip request(int rider_id, double lat, double lng) {
        // Write your code here
    }
}
```

## TAG
**Hash Table**