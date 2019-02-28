// Use RE
// url is wrapped by href = "". 
// Pay attetion to:
// 1. Random spaces.
// 2. Not null
// 3. Not #

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
    private static final String HTML_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"|')+([^\"'>\\s]*)";
    /**
     * @param content source code
     * @return a list of links
     */
    public List<String> parseUrls(String content) {
		List<String> links = new ArrayList<String>();
		Pattern pattern = Pattern.compile(HTML_HREF_TAG_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		String url = null;
		while (matcher.find()) {
			url = matcher.group(2);
			if (url.length() == 0 || url.startsWith("#")) {
				continue;
			}
			links.add(url);
		}
		
		return links;
	}
}