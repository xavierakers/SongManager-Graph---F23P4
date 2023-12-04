/**
 * @author Xavier Akers
 * @author Zoe Hite
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
    private GraphL graph;
    private int totalRecords;

    /**
     * Constructor
     * 
     * @param hashSize
     *            Size of the Hash Tables
     */
    public Controller(int hashSize) {
        songTable = new Hash(hashSize);
        artistTable = new Hash(hashSize);
        graph = new GraphL();
        graph.init(hashSize);
        totalRecords = 0;
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

        Record artistRecord = new Record(artist, totalRecords);
        int oldArtistThresh = artistTable.getThreshold();
        boolean artistInserted = artistTable.insert(artistRecord);

        // Need to add check for duplicate
        if (artistTable.getCount() > oldArtistThresh) {
            System.out.println("Artist hash table size doubled.");
        }

        // Inserting in hash table
        if (artistInserted) {
            System.out.printf("|%s| is added to the Artist database.%n",
                artist);

            totalRecords++;
        }

        if (songTable.getCount() == songTable.getThreshold()) {
            System.out.println("Song hash table size doubled.");
        }

        Record songRecord = new Record(song, totalRecords);
        boolean songInserted = songTable.insert(songRecord);
        if (songInserted) {
            System.out.printf("|%s| is added to the Song database.%n", song);
            totalRecords++;
        }
        // Both artist and song were just inserted
        if (artistInserted && songInserted) {
            int index1 = graph.addNode();
            int index2 = graph.addNode();
            graph.addEdge(index1, index2, 1);
            graph.addEdge(index1, index2, 1);
        }
        // Artist exists, song inserted
        int artistGraphIndex = artistTable.search(artist).getValue();
        if (!artistInserted && songInserted) {
            int newIndex = graph.addNode();
            graph.addEdge(artistGraphIndex, newIndex, 1);
            graph.addEdge(newIndex, artistGraphIndex, 1);
        }
        // Song exists, artist inserted
        int songGraphIndex = songTable.search(song).getValue();
        if (artistInserted && !songInserted) {
            int newIndex = graph.addNode();
            graph.addEdge(songGraphIndex, newIndex, 1);
            graph.addEdge(newIndex, songGraphIndex, 1);
        }
        // Both exist and are already linked
        else if (graph.hasEdge(artistGraphIndex, songGraphIndex)
            && !artistInserted && !songInserted) {
            System.out.printf(
                "|%s<SEP>%s| duplicates a record already in the database.%n",
                artist, song);
        }
        // Both exist and are not linked
        else if (!graph.hasEdge(artistGraphIndex, songGraphIndex)) {
            graph.addEdge(songGraphIndex, artistGraphIndex, 1);
            graph.addEdge(artistGraphIndex, songGraphIndex, 1);
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
                // if this artist exists
                Record artist = artistTable.delete(param);
                if (artist != null) {
                    graph.removeNode(artist.getValue());
                    System.out.printf(
                        "|%s| is removed from the Artist database.%n", param);

                    return true;
                }
                System.out.printf(
                    "|%s| does not exist in the Artist database.%n", param);
                break;
            case "song":
                Record song = songTable.delete(param);
                if (song != null) {
                    graph.removeNode(song.getValue());
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


    /**
     * Prints Information about the graph
     */
    public void printGraph() {

        graph.analyzeGraph();

        System.out.printf("There are %d connected components%n", graph
            .getNumConnectedComponents());
        System.out.printf("The largest connected component has %d elements%n",
            graph.getBiggestComponentCount());
        System.out.printf("The diameter of the largest component is %d%n", graph
            .getBiggestComponentDiameter());
    }

}
