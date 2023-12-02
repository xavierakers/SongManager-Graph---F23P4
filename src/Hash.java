/**
 * Hash table class
 *
 * @author Xavier Akers
 * @author Zoe Hite
 * @version Last Updated 11-12-2023
 * @since 11-5-2023
 */

public class Hash {
    /**
     * Deleted record placeholder
     */
    static final Record TOMBSTONE = new Record(null, -1);
    private Record[] table;
    private int count;
    private int capacity;
    private int threshold;

    /**
     * Hash Table constructor
     * 
     * @param size
     *            Number of slots in the hash table
     */
    public Hash(int size) {
        this.capacity = size;
        this.table = new Record[size];
        this.count = 0;
        this.threshold = (int)(capacity / 2);
    }


    /**
     * Insert the record into the hash table
     * 
     * @param record
     *            Record Object storing KV Pair
     * @return True if successfully inserted
     */
    public boolean insert(Record record) {
        if (count == threshold) {
            resize();
        }
        int home = h(record.getKey(), capacity);
        int step = 0;
        int index = home;
        if (search(record.getKey()) == null) {
            while ((table[index] != null) && (table[index] != TOMBSTONE)) {
                step++;
                index = (home + q(step)) % capacity;
            }
            table[index] = record;
            count++;
            return true;
        }

        return false;
    }


    /**
     * Searched for matching record
     * 
     * @param key
     *            The record key
     * @return The matching record, null if non-existant
     */
    public Record search(String key) {
        int home = h(key, capacity);
        int step = 0;
        int index = home;

        while (table[index] != null) {
            if (!table[index].equals(TOMBSTONE)) {
                if (table[index].getKey().equals(key)) {
                    return table[index];
                }
            }
            step++;
            index = (home + q(step)) % capacity;

        }
        return null;
    }


    /**
     * Removes record from table
     * 
     * @param key
     *            Record key
     * @return Deleted record, null if non-existant
     */
    public Record delete(String key) {
        int home = h(key, capacity);
        int step = 0;
        int index = home;

        while (table[index] != null) {
            if (table[index].getKey() != null) {
                if (table[index].getKey().equals(key)) {
                    Record record = table[index];
                    table[index] = TOMBSTONE;
                    count--;
                    return record;
                }

            }
            step++;
            index = (home + q(step)) % capacity;
        }
        return null;
    }


    /**
     * Doubles the hash table size
     * Removes tombstones
     */
    private void resize() {
        capacity *= 2;
        threshold = (int)(capacity / 2);

        Record[] newTable = new Record[capacity];

        for (Record value : table) {
            if (value != null && value != TOMBSTONE) {
                int home = h(value.getKey(), capacity);
                int step = 0;
                int index = home;

                while (newTable[index] != null) {
                    step++;
                    index = (home + q(step) % capacity);
                }
                newTable[index] = value;
            }
        }
        table = newTable;
    }


    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }


    /**
     * Quadratic probing function
     * 
     * @param i
     *            The ith step in the probe sequence
     * @return Step value
     */
    public static int q(int i) {
        return i * i;
    }


    /**
     * @return Number of items in the table
     */
    public int getCount() {
        return count;
    }


    /**
     * @return Max number of items before resizing
     */
    public int getThreshold() {
        return threshold;
    }


    /**
     * Prints the contents with its respective indexes
     */
    public void printContents() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                if (table[i].getKey() == null) {
                    System.out.printf("%d: TOMBSTONE%n", i);
                }
                else {
                    System.out.printf("%d: |%s|%n", i, table[i].getKey());
                }
            }

        }
    }
}
