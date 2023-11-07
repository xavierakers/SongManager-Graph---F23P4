
public class Record {
    private String key;
    private GraphNode value;

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
