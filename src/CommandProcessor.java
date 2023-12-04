import java.io.File;
import java.util.Scanner;

/**
 * @author Xavier Akers
 * @author Zoe Hite
 * 
 * @version Last Updated 11-12-2023
 * 
 * @since 11-12-2023
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
            String[] param;
            // Isolate the command
            String[] cmd = new String[1];

            // Scan until EOF
            while (sc.hasNextLine()) {
                cmd = sc.nextLine().trim().split("\\s+", 2);
                // Skips the random newlines in the input file
                // if (!cmd[0].isEmpty()) {
                // System.out.print(cmd[0] + " ");
                switch (cmd[0]) {
                    case "insert":
                        param = cmd[1].trim().split("<SEP>");
                        controller.insert(cmd[1]);
                        break;

                    case "remove":
                        param = cmd[1].trim().split("\\s+", 2);
                        controller.remove(param[0], param[1]);
                        break;

                    case "print":
                        param = cmd[1].trim().split("\\s+");
                        switch (param[0]) {
                            case "graph":
                                controller.printGraph();
                                break;
                            default:
                                controller.printCount(param[0]);
                        }
                        break;
                    default:
                        // System.out.println("Invalid command");
                        break;
                }
            }
            // }
            sc.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
