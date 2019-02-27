// Use 2 hash table:
// One store short -> long; One stores long -> short

// In order to avoid duplication, we can either use dictionary order or randomly generate one based on a Hash Set.

// ========== Version 1: Random =============
public class TinyUrl {
	private Map<String, String> long2short;
	private Map<String, String> short2long;
	
	public TinyUrl() {
		long2short = new HashMap<String, String>();
		short2long = new HashMap<String, String>();
	}
	
    /*
     * @param url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
		if (long2short.containsKey(url)) {
			return long2short.get(url);
		}
		
		while (true) {
			String shortURL = generateShortURL();
			if (!short2long.containsKey(shortURL)) {
				short2long.put(shortURL, url);
				long2short.put(url, shortURL);
				return shortURL;
			}
		}
    }

    /*
     * @param url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String url) {
		if（!short2long.containsKey(url)) {
			return null;
		}
		
		return short2long.get(url);
    }
	
	private String generateShortURL() {
		String allowedChars = "0123456789" + "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		Random rand = new Random();
		String shortURL = "http://tiny.url/";
		
		for (int i = 0; i < 6; i++) {
			int index = rand.nextInt(62);
			shortURL += allowedChars.charAt(i);
		}
		
		return shortURL;
	}
}

// ========== Version 2: Base62 =============
public class TinyUrl {
	public static int GLOBAL_ID = 0;
    private HashMap<Integer, String> id2url = new HashMap<Integer, String>();
    private HashMap<String, Integer> url2id = new HashMap<String, Integer>();
	
	private String getShortKey(String url) {
		return url.substring("http://tiny.url/".length());
	}
	
	private int toBase62(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        return c - 'A' + 36;
	}
	
	private int shortKeyToID(String shortKey) {
		int id = 0;
		for (int i = 0; i < shortKey.length(); i++) {
			id = id * 62 + toBase62(shortKey.charAt(i));
		}
		return id;
	}
	
	private String idToShortKey(int id) {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shortUrl = "";
		while (id > 0) {
			shortUrl = chars.charAt(id % 62) + shortUrl;
			id = id / 62;
		}
		
		while (shortUrl.length() < 6) {
			shortUrl = "0" + shortUrl;
		}
		
		return shortUrl;
	}
	
    /*
     * @param url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
		if (url2id.containsKey(url)) {
			return "http://tiny.url/" + idToShortKey(url2id.get(url));
		}
		
		GLOBAL_ID++;
        url2id.put(url, GLOBAL_ID);
        id2url.put(GLOBAL_ID, url);
        return "http://tiny.url/" + idToShortKey(GLOBAL_ID);
	}

    /*
     * @param url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String url) {
        String shortKey = getShortKey(url);
		int id = shortKeyToID(shortKey);
		return id2url.get(id);
    }
}