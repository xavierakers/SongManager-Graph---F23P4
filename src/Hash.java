/**
 * Hash table class
 *
 * @author Xavier Akers
 * @author Zoe Hite
 * @version Last Updated
 */

public class Hash {
    /**
     * Deleted record placeholder
     */
    static final Record TOMBSTONE = new Record(null, null);
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


    public Record delete(String key) {
        int home = h(key, capacity);
        int step = 0;
        int index = home;

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                Record record = table[index];
                table[index] = TOMBSTONE;
                count--;
                return record;
            }
            step++;
            index = (home + q(step)) % capacity;
        }
        return null;
    }


    private void resize() {
        capacity *= 2;
        threshold = (int)(capacity / 2);

        Record[] newTable = new Record[capacity];

        for (Record value : table) {
            if (value != null && value != TOMBSTONE) {
                int home = h(value.getKey(), count);
                int step = 0;
                int index = home;

                while (newTable[index] != null) {
                    step++;
                    index = (home + q(step) % count);
                }
                newTable[index] = value;
            }
        }
        table = newTable;
        System.out.printf("Song hash table size doubled.%n");
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
     * @return
     */
    public static int q(int i) {
        return i * i;
    }
}
