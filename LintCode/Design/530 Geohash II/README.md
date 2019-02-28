## Description
This is a followup question for [Geohash](https://github.com/weltond/DataStructure/tree/master/LintCode/Design/529%20Geohash).

Convert a Geohash string to latitude and longitude.

Try http://geohash.co/.

Check how to generate geohash on wiki: Geohash or just google it for more details.

## Example
Example1
```
Input: "wx4g0s"`
Output: lat = 39.92706299 and lng = 116.39465332
```

Example2
```
Input: "w"
Output: lat = 22.50000000 and lng = 112.50000000
```

## TO DO
```java
public class GeoHash {
    /*
     * @param geohash: geohash a base32 string
     * @return: latitude and longitude a location coordinate pair
     */
    public double[] decode(String geohash) {
        // write your code here
    }
}
```

## TAG
**Binary Search**