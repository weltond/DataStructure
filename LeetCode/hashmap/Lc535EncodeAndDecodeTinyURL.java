// https://leetcode.com/problems/encode-and-decode-tinyurl/

public class Codec {
    // ============== Method 2: Global ID (Naive) ====================
    // CANNOT pass. WHY?
    public static int GLOBAL_ID = 0;
    private Map<Integer, String> id2url = new HashMap();    // <longUrl, GLOBAL_ID>
    private Map<String, Integer> url2id = new HashMap();    // <GLOBAL_ID, longUrl>
    private int prefixLength = "http://tinyurl.com/".length();
    public String encode(String longUrl) {
        if (url2id.containsKey(longUrl)) return "http://tiny.url/" + idToShortUrlKey(url2id.get(longUrl));
        
        GLOBAL_ID++;
        
        url2id.put(longUrl, GLOBAL_ID);
        id2url.put(GLOBAL_ID, longUrl);
        
        //System.out.println(idToShortUrlKey(GLOBAL_ID));
        return "http://tiny.url/" + idToShortUrlKey(GLOBAL_ID);
    }
    
    public String decode(String shortUrl) {
        String shortKey = getShortUrlKey(shortUrl);
        int globalId = shortUrlKeyToId(shortKey);
        //System.out.println(id2url.containsKey(globalId));
        return id2url.get(globalId);
    }
    
    private String getShortUrlKey(String shortUrl) {
        return shortUrl.substring(prefixLength);
    }
    
    // convert char to 0 - 62 based on '0-9a-zA-z'
    private int toBase62(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        else if (c >= 'a' && c <= 'z') return c - 'a' + 10;
        else return c - 'A' + 36;
    }
    
    // convert short url to GLOBAL_ID
    private int shortUrlKeyToId(String shortUrlKey) {
        int id = 0, len = shortUrlKey.length();
        char[] arr = shortUrlKey.toCharArray();
        for (int i = 0; i < len; i++) {
            id = id * 62 + toBase62(arr[i]);
        }
        return id;
    }
    
    // convert GLOBAL_ID to short url
    private String idToShortUrlKey(int id) {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder shortUrlKey = new StringBuilder();
        char[] arr = chars.toCharArray();
        while (id > 0) {
            shortUrlKey.append(arr[id % 62]);
            id = id / 62;
        }
        
        // if short url length is less than 6, add 0 in front of it
        while (shortUrlKey.length() < 6) {
            shortUrlKey.insert(0, "0");
        }
        
        return shortUrlKey.toString();
    }
    
    // ============== Method 1: Hash Table ====================
    // 3ms (75.26%)
    private Map<String, String> long2short = new HashMap();
    private Map<String, String> short2long = new HashMap();
    
    Random rand = new Random();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        // in case same longurl has different short urls
        if (long2short.containsKey(longUrl)) return long2short.get(longUrl);
        
        while (true) {  // avoid duplicate shorturls
            String shortURL = generateURL();
            if (!short2long.containsKey(shortURL)) {
                short2long.put(shortURL, longUrl);
                long2short.put(longUrl, shortURL);
                return shortURL;
            }
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (!short2long.containsKey(shortUrl)) return null;
        
        return short2long.get(shortUrl);
    }
    
    private String generateURL() {
        String allowedChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] arr = allowedChars.toCharArray();
        
        String shortURL = "http://tinyurl.com/";
        
        StringBuilder sb = new StringBuilder();
        sb.append(shortURL);
        for (int i = 0; i < 6; i++) {
            int idx = rand.nextInt(62);
            sb.append(arr[idx]);
        }
        
        return sb.toString();
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
