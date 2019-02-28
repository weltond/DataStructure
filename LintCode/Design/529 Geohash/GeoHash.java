public class GeoHash {
    /**
     * @param latitude, longitude a location coordinate pair 
     * @param precision an integer between 1 to 12
     * @return a base32 string
     */
    public String encode(double latitude, double longitude, int precision) {
        // Write your code here
        String _base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        String lat_bin = getBin(latitude, -90, 90);
        String lng_bin = getBin(longitude, -180, 180);
        
        System.out.println("LAT: " + lat_bin + ", LNG: " + lng_bin);
        
        StringBuffer hash_code = new StringBuffer();
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < 30; ++i) {
            b.append(lng_bin.charAt(i));
            b.append(lat_bin.charAt(i));
        }
        System.out.println("b: " + b);
        for (int i = 0; i < 60; i += 5) {
            int index = b2i(b.substring(i, i + 5)); 
            hash_code.append(_base32.charAt(index));
        }
        System.out.println("hash_code: " + hash_code);
        
        return hash_code.substring(0, precision);
    }

    public int b2i(String bin) {
        return Integer.parseInt(bin, 2);
    }

    public String getBin(double value, double left, double right) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < 30; ++i) {
            double mid = (left + right) / 2.0;
            if (value > mid) {
                left = mid;
                b.append("1");
            } else {
                right = mid;
                b.append("0");
            }
        }
        return b.toString();
    }
}

/**
E.g 1:
  Input
		-90
		180
		12

  Your stdout
		LAT: 000000000000000000000000000000, LNG: 111111111111111111111111111111
		b: 101010101010101010101010101010101010101010101010101010101010
		hash_code: pbpbpbpbpbpb
  
  Output
		"pbpbpbpbpbpb"
		Expected
		"pbpbpbpbpbpb"

E.g 2:
  Input
		39.92816697
		116.38954991
		12

  Your stdout
		LAT: 101110001100100101100110111001, LNG: 110100101100010000010010001010
		b: 111001110100100011110000011000010001011000011100010111001001
		hash_code: wx4g0s8q3jf9

  Output
		"wx4g0s8q3jf9"
		Expected
		"wx4g0s8q3jf9"
*/