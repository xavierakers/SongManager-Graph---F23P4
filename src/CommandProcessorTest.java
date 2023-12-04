import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Xavier Akers
 * @author Zoe Hite
 * 
 * @version Last Updated 11-12-2023
 * 
 * @since 11-12-2023
 * 
 *        Command Processor Class Tests
 * 
 */
public class CommandProcessorTest extends TestCase {
    // Creating SeminarDB to handle function calls
    private Controller controller = new Controller(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

    /**
     * Testing hasNextLine() mutation
     * Reads in two different command files
     * 
     * @throws IOException
     */
    public void testHasNextLine() throws IOException {
        cp.readCommands("testInputFiles/testHasNextLine.txt");

        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testHasNextLine_output.txt")));
        assertEquals(expected, systemOut().getHistory());

        cp.readCommands("testInputFiles/testHasNextLine.txt");

        expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testHasNextLine_output2.txt")));
        assertEquals(expected, systemOut().getHistory());
    }

}
