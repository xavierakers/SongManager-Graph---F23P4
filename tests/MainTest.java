import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Xavier Akers
 * @author Zoe Hite
 * 
 * @version Last Updated 2023-16-11
 * 
 *          Testing with input and output files
 */
public class MainTest extends TestCase {
    /**
     * Code coverage
     */
    public void testMain() {
        String[] args = new String[] { "8", "testInput.txt" };
        GraphProject.main(args);
    }


    /**
     * Testing non repeated entries, not duplicates
     * 
     * @throws IOException
     */
    public void testDuplicateArtist() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testDuplicateArtist.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testDuplicateArtist_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Adding some records
     * 
     * @throws IOException
     */
    public void testInsertSimple() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Inserts cause expansion of hash table
     * 
     * @throws IOException
     */
    public void testInsertBasic() throws IOException {
        String[] args = new String[] { "4",
            "testInputFiles/testInsertBasic.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertBasic_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Expansion of Hash table with tombstones
     * 
     * @throws IOException
     */
    public void testInsertComplex() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Testing simple removal
     * 
     * @throws IOException
     */
    public void testRemoveSimple() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testRemoveSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testRemoveSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Delete records, expand, delete records again
     * 
     * @throws IOException
     */
    public void testRemoveBasic() throws IOException {
        String[] args = new String[] { "4",
            "testInputFiles/testRemoveBasic.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testRemoveBasic_output.txt")));

        assertEquals(expected, systemOut().getHistory());

    }


    public void testSampleInput() {
        String[] args = new String[] { "10", "P4sampleInput.txt" };
        GraphProject.main(args);
    }

}
