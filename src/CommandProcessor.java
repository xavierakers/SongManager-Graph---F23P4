import java.io.File;
import java.util.Scanner;

/**
 * @author Xavier Akers
 * 
 * @version Last Updated
 * 
 * @since 2023-09-21
 * 
 *        A simple file parser
 * 
 */
public class CommandProcessor {
    private Controller controller;

    /**
     * Constructor to receive a controller
     * 
     * @param controller
     *            Controls the HashTable and MemoryManager
     */
    public CommandProcessor(Controller controller) {
        this.controller = controller;
    }


    /**
     * 1. Read commands
     * 2. Read in following arguments
     * 3. Send arguments to respective DB function
     * 
     * @param filename
     *            A string containing the input file
     */
    public void readCommands(String filename) {
        try {
            // Input scanner
            Scanner sc = new Scanner(new File(filename));
            // Buffer to read in lines
            String[] line;
            // Isolate the command
            String cmd;
            // Isolate the ID
            String id = null;

            // Scan until EOF
            while (sc.hasNextLine()) {

                line = sc.nextLine().trim().split("\\s+");
                cmd = line[0].trim();
                // If there was an ID associated with the command
                if (line.length > 1) {
                    id = line[1].trim();
                }

                // Skips the random newlines in the input file
                if (!cmd.isEmpty()) {
                    switch (cmd) {
                        case "insert":
                            // Array to store input data
                            // line[1];
                            break;
                        case "remove":
                            switch (line[1].trim()) {
                                case "artist":
                                    break;
                                case "song":
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "print":
                            switch (line[1].trim()) {
                                case "artist":
                                    break;
                                case "song":
                                    break;
                                case "graph":
                                    break;
                                default:
                                    break;
                            }
                        default:
                            System.out.println("Invalid command");
                    }
                }
            }
            sc.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
