/**
 * @author Xavier Akers
 * 
 * @version Last Updated 11-5-2023
 * 
 * @since 11-5-2023
 * 
 *        Modified KV Pair
 * 
 */
public class Record {
    private String key;
    private GraphNode value;

    /**
     * @param key
     *            Key value
     * @param value
     *            GraphNode
     */
    Record(String key, GraphNode value) {
        this.key = key;
        this.value = value;
    }


    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }


    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * @return the value
     */
    public GraphNode getValue() {
        return value;
    }


    /**
     * @param value
     *            the value to set
     */
    public void setValue(GraphNode value) {
        this.value = value;
    }

}
