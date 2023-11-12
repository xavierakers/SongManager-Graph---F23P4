
public class Controller {
    private Hash songTable;
    private Hash artistTable;

    public Controller(int hashSize) {
        songTable = new Hash(hashSize);
        artistTable = new Hash(hashSize);
    }


    public boolean insert(String input) {
        String[] record = input.trim().split("<SEP>");
        String artist = record[0];
        String song = record[1];
        Record artistRec = new Record(artist, new GraphNode(-1));
        Record songRec = new Record(song, new GraphNode(-1));

        // Need to add check for duplicate
        if (artistTable.insert(artistRec)) {
            System.out.printf("|%s| is added to the Artist database.%n",
                artist);
        }
        if (songTable.insert(songRec)) {
            System.out.printf("|%s| is added to the Song database.%n", song);
        }

        return true;
    }


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


    public boolean printCount(String table) {
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

        return false;
    }
}
