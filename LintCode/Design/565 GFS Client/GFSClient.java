/* Definition of BaseGFSClient
 * class BaseGFSClient {
 *     private Map<String, String> chunk_list;
 *     public BaseGFSClient() {}
 *     public String readChunk(String filename, int chunkIndex) {
 *         // Read a chunk from GFS
 *     }
 *     public void writeChunk(String filename, int chunkIndex,
 *                            String content) {
 *         // Write a chunk to GFS
 *     }
 * }
 */


public class GFSClient extends BaseGFSClient {
	public int chunkSize;
	// String: File name
	// Integer: number of chunks for the file
	public Map<String, Integer> chunkNum;
	
    /*
    * @param chunkSize: An integer
    */public GFSClient(int chunkSize) {
        // do intialization if necessary
		this.chunkSize = chunkSize;
		this.chunkNum = new HashMap<String, Integer>();
    }

    /*
     * @param filename: a file name
     * @return: conetent of the file given from GFS
     */
    public String read(String filename) {
        // write your code here
		if (!chunkNum.containsKey(filename))
			return null;
		
		StringBuffer content = new StringBuffer();
		
		for (int i = 0; i < chunkNum.get(filename); ++i) {
			String sub_content = readChunk(filename, i);
			if (sub_content != null) {
				content.append(sub_content);
			}
		}
		
		return content.toString();
    }

    /*
     * @param filename: a file name
     * @param content: a string
     * @return: nothing
     */
    public void write(String filename, String content) {
        // write your code here
		int length = content.length();
		
		
		// calculate how many chunks we need
		int num = (length - 1) / chunkSize + 1;
		chunkNum.put(filename, num);
		
		for (int i = 0; i < num; i++) {
			int start = i * chunkSize;
			int end = (i == num - 1) ? length : (i + 1) * chunkSize;
			
			String sub_content = content.substring(start, end);
			writeChunk(filename, i, sub_content);
		}
    }
}