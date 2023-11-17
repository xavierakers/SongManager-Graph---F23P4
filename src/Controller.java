/**
 * @author Xavier Akers
 * 
 * @version Last Updated 11-12-2023
 * 
 * @since 11-12-2023
 * 
 *        Handles Record and Graph Integration
 * 
 */
public class Controller {
    private Hash songTable;
    private Hash artistTable;

    /**
     * Constructor
     * 
     * @param hashSize
     *            Size of the Hash Tables
     */
    public Controller(int hashSize) {
        songTable = new Hash(hashSize);
        artistTable = new Hash(hashSize);
    }


    /**
     * Parses the record and input into correct hash table
     * 
     * @param input
     *            Literal String Input
     * @return True if inserted successfully
     */
    public boolean insert(String input) {
        String[] record = input.trim().split("<SEP>");
        String artist = record[0];
        String song = record[1];
        Record artistRec = new Record(artist, new GraphNode(-1));
        Record songRec = new Record(song, new GraphNode(-1));

        // Need to add check for duplicate
        if (artistTable.getCount() == artistTable.getThreshold()) {
            System.out.println("Artist hash table size doubled.");
        }
        if (artistTable.insert(artistRec)) {

            System.out.printf("|%s| is added to the Artist database.%n",
                artist);
        }

        if (songTable.getCount() == songTable.getThreshold()) {
            System.out.println("Song hash table size doubled.");
        }
        if (songTable.insert(songRec)) {

            System.out.printf("|%s| is added to the Song database.%n", song);
        }

        return true;
    }


    /**
     * Removes the Record from the respective hash table
     * 
     * @param table
     *            Hash Table to check
     * @param param
     *            Record to remove
     * @return True if successfully removed from Hash table
     */
    public boolean remove(String table, String param) {
        switch (table) {
            case "artist":
                if (artistTable.delete(param) != null) {
                    System.out.printf(
                        "|%s| is removed from the Artist database.%n", param);
                    return true;
                }
                System.out.printf(
                    "|%s| does not exist in the Artist database.%n", param);
                break;
            case "song":
                if (songTable.delete(param) != null) {
                    System.out.printf(
                        "|%s| is removed from the Song database.%n", param);
                    return true;
                }
                System.out.printf("|%s| does not exist in the Song database.%n",
                    param);
                break;
            default:
                break;
        }
        return false;
    }


    /**
     * Prints contents of respective hash table
     * 
     * @param table
     *            Hash table to print
     */
    public void printCount(String table) {
        switch (table) {
            case "artist":
                artistTable.printContents();
                System.out.printf("total artists: %d%n", artistTable
                    .getCount());
                break;
            case "song":
                songTable.printContents();
                System.out.printf("total songs: %d%n", songTable.getCount());
                break;
            default:
                break;
        }
    }
}
