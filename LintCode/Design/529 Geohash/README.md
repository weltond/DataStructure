## Description
Geohash is a hash function that convert a location coordinate pair into a base32 string.

Check how to generate geohash on wiki: Geohash or just google it for more details.

Try http://geohash.co/.

You task is converting a (latitude, longitude) pair into a geohash string.

1 <= precision <=12

## Example

Example1
```
Input: 
lat = 39.92816697 
lng = 116.38954991
precision = 12 
Output: "wx4g0s8q3jf9"
```

Example2
```
Input: 
lat = -90
lng = 180
precision = 12 
Output: "pbpbpbpbpbpb"
```

## TO DO
```java
public class GeoHash {
    /*
     * @param latitude: one of a location coordinate pair 
     * @param longitude: one of a location coordinate pair 
     * @param precision: an integer between 1 to 12
     * @return: a base32 string
     */
    public String encode(double latitude, double longitude, int precision) {
        // write your code here
    }
}
```

## TAG
**Mathematics**
